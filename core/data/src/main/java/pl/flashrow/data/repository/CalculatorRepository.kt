package pl.flashrow.data.repository

import pl.flashrow.data.model.BeverageTypeDto
import pl.flashrow.data.model.ContainerTypeDto
import pl.flashrow.data.model.CoolingEnvironmentDto

interface CalculatorRepository {
    fun getDrinkTypes() : List<BeverageTypeDto>
    fun getContainerTypes() : List<ContainerTypeDto>
    fun getCoolingPlaces() : List<CoolingEnvironmentDto>
}