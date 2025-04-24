package pl.flashrow.data.model

data class ContainerTypeDto(
    val name: String,
    val capacityMl: Int,
    val material: String,
    val surfaceArea: Double, // surface are in m^2
)
