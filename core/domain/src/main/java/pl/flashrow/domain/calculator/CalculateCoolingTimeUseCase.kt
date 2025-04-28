package pl.flashrow.domain.calculator

import pl.flashrow.dcc.core.model.BeverageType
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.dcc.core.model.CoolingEnvironment
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.ln
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class CalculateCoolingTimeUseCase @Inject constructor() {
    operator fun invoke(
        beverageType: BeverageType,
        container: ContainerType,
        coolingEnvironment: CoolingEnvironment,
        drinkStartTemperature: Float,
        drinkTargetTemperature: Float,
    ) : Result<Duration>{
        if (drinkTargetTemperature >= drinkStartTemperature) {
            return Result.failure(IllegalArgumentException("Temperatura docelowa musi być niższa niż początkowa."))
        }
        if (drinkTargetTemperature < coolingEnvironment.temperature) {
            return Result.failure(IllegalArgumentException("Temperatura docelowa nie może być niższa niż temperatura otoczenia."))
        }
        if (drinkStartTemperature <= coolingEnvironment.temperature) {
            return Result.success(0.seconds)
        }

        val T_initial_K = drinkStartTemperature.toDouble() + 273.15
        val T_target_K = drinkTargetTemperature.toDouble() + 273.15
        val T_ambient_K = coolingEnvironment.temperature + 273.15

        val c = beverageType.specificHeat // specific heat [J/(kg·K)]
        val rho = beverageType.density     // density [kg/m³]
        val A = container.surfaceArea  // surface [m²]
        val V = container.capacity       // volume [m³]
        val h = coolingEnvironment.convectionCoefficient // convection coefficient [W/(m²·K)][1, 2, 3]

        // 1. Heat capacity calculation (C) [J/K]
        val C = c * rho * V
        if (C <= 0 ||!C.isFinite()) {
            return Result.failure(ArithmeticException("Nieprawidłowa obliczona pojemność cieplna (C): $C"))
        }

        // 2. Cooling Coefficient Calculation (k) [1/s][4]
        val k = (h * A) / C
        if (k <= 0 || !k.isFinite()) {
            return Result.failure(ArithmeticException("Nieprawidłowy obliczony współczynnik chłodzenia (k): $k"))
        }

        // 3. Calculating the argument of the natural logarithm [4]
        val targetAmbientDifference = T_target_K - T_ambient_K
        val initialAmbientDifference = T_initial_K - T_ambient_K

        // Checking if T_target is very close to T_ambient (avoiding ln(0))
        if (abs(targetAmbientDifference) < 1e-9) {
            // Theoretically, it takes infinite time to reach T_ambient.
            // You can return an error or a very large value, depending on your requirements.
            return Result.failure(ArithmeticException("Osiągnięcie temperatury otoczenia (${coolingEnvironment.temperature}°C) zajmuje teoretycznie nieskończony czas."))
        }
        // Check if T_initial is equal to T_ambient (divide by zero) - handled at the beginning
         if (abs(initialAmbientDifference) < 1e-9) { return Result.success(0.seconds) }

        val ratio = targetAmbientDifference / initialAmbientDifference

        // Validation of the logarithm argument (should be > 0 and < 1 for cooling)
        if (ratio <= 0 || ratio >= 1) {
        return Result.failure(ArithmeticException("Nieprawidłowy argument dla logarytmu naturalnego (ratio): $ratio. Sprawdź temperatury wejściowe."))
        }

        // --- Obliczenie czasu chłodzenia (t) [s] --- [4]
        val t_seconds = -ln(ratio) / k

        if (!t_seconds.isFinite() || t_seconds < 0) {
            return Result.failure(ArithmeticException("Obliczony czas jest nieprawidłowy: $t_seconds sekund."))
        }

        // --- Konwersja na Duration i zwrócenie wyniku ---
        val duration = t_seconds.seconds

        return Result.success(duration)
    }
}