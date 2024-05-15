package pl.flashrow.dcc.core.model

import pl.flashrow.dcc.core.enum.Material

data class ContainerType(
    val name: String,
    val capacityMl: Int,
    val material: Material,
)
