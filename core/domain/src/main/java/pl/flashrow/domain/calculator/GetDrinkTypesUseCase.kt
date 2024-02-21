package pl.flashrow.domain.calculator

import pl.flashrow.data.repository.CalculatorRepository
import pl.flashrow.dcc.core.mapper.mapDrinkTypeDtoToDrinkType
import pl.flashrow.dcc.core.model.DrinkType
import javax.inject.Inject

class GetDrinkTypesUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {
    operator fun invoke(): List<DrinkType> =
        calculatorRepository.getDrinkTypes().map {
            mapDrinkTypeDtoToDrinkType(it)
        }
}