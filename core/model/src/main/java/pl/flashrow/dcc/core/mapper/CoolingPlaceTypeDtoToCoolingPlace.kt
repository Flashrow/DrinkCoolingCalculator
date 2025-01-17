package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.CoolingPlaceDto
import pl.flashrow.dcc.core.enum.CoolingPlaceType
import pl.flashrow.dcc.core.model.CoolingPlace

fun mapCoolingPlaceTypeDtoToCoolingPlace(coolingPlaceDto: CoolingPlaceDto): CoolingPlace = CoolingPlace(
    coolingPlaceType = CoolingPlaceType.valueOf(coolingPlaceDto.coolingPlaceType),
    name = coolingPlaceDto.name,
    temperature = coolingPlaceDto.temperature,
)