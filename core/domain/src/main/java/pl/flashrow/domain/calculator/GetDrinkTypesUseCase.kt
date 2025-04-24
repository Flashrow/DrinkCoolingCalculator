package pl.flashrow.domain.calculator

import pl.flashrow.data.repository.CalculatorRepository
import pl.flashrow.dcc.core.mapper.mapDrinkTypeDtoToDrinkType
import pl.flashrow.dcc.core.model.BeverageType
import javax.inject.Inject

class GetDrinkTypesUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {
    operator fun invoke(): List<BeverageType> =
        calculatorRepository.getDrinkTypes().map {
            mapDrinkTypeDtoToDrinkType(it)
        }
}