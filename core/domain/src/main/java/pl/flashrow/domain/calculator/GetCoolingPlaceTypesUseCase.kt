package pl.flashrow.domain.calculator

import pl.flashrow.data.repository.CalculatorRepository
import pl.flashrow.dcc.core.mapper.mapCoolingPlaceTypeDtoToCoolingPlace
import pl.flashrow.dcc.core.model.CoolingPlace
import javax.inject.Inject

class GetCoolingPlaceTypesUseCase @Inject constructor(private val calculatorRepository: CalculatorRepository) {
    operator fun invoke(): List<CoolingPlace> =
        calculatorRepository.getCoolingPlaces().map { mapCoolingPlaceTypeDtoToCoolingPlace(it) }
}