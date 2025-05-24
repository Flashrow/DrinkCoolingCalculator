package pl.flashrow.data.model

data class ContainerTypeDto(
    val nameResourceId: Int,
    val capacity: Double,
    val material: String,
    val surfaceArea: Double, // surface are in m^2
)
