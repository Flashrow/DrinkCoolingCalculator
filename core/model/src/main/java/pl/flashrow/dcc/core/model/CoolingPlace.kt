package pl.flashrow.dcc.core.model

import pl.flashrow.dcc.core.enum.CoolingPlaceType

data class CoolingPlace(
    val coolingPlaceType: CoolingPlaceType,
    val name: String,
    val temperature: Int? = null,
)
