package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.CoolingEnvironmentDto
import pl.flashrow.dcc.core.enum.CoolingPlaceType
import pl.flashrow.dcc.core.model.CoolingEnvironment

fun mapCoolingPlaceTypeDtoToCoolingPlace(coolingEnvironmentDto: CoolingEnvironmentDto): CoolingEnvironment = CoolingEnvironment(
    coolingPlaceType = CoolingPlaceType.valueOf(coolingEnvironmentDto.coolingPlaceType),
    nameResourceId = coolingEnvironmentDto.nameResourceId,
    temperature = coolingEnvironmentDto.temperature,
    convectionCoefficient = coolingEnvironmentDto.convectionCoefficient,
)