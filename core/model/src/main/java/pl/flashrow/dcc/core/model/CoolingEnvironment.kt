package pl.flashrow.dcc.core.model

import pl.flashrow.dcc.core.enum.CoolingPlaceType

data class CoolingEnvironment(
    val coolingPlaceType: CoolingPlaceType,
    val nameResourceId: Int,
    val temperature: Double,
    val convectionCoefficient: Double,
)
