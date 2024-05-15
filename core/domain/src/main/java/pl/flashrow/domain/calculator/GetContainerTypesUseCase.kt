package pl.flashrow.domain.calculator

import pl.flashrow.data.repository.CalculatorRepository
import pl.flashrow.dcc.core.mapper.mapContainerTypeDtoToContainerType
import pl.flashrow.dcc.core.model.ContainerType
import javax.inject.Inject

class GetContainerTypesUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {
    operator fun invoke(): List<ContainerType> =
        calculatorRepository.getContainerTypes().map {
            mapContainerTypeDtoToContainerType(it)
        }
}