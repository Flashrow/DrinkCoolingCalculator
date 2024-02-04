package pl.flashrow.domain

import pl.flashrow.data.model.DrinkTypeDto
import pl.flashrow.data.repository.CalculatorRepository
import javax.inject.Inject

class GetDrinkTypesUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {
    operator fun invoke(): List<DrinkTypeDto> =
        calculatorRepository.getDrinkTypes()
}