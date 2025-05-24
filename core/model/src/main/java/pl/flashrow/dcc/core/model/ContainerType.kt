package pl.flashrow.dcc.core.model

import pl.flashrow.dcc.core.enum.Material

data class ContainerType(
    val nameResourceId: Int,
    val capacity: Double, // M^3
    val material: Material,
    val surfaceArea: Double, // surface are in m^2
)
