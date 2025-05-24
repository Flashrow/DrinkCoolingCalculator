package pl.flashrow.data.model

data class CoolingEnvironmentDto(
    val coolingPlaceType: String,
    val nameResourceId: Int,
    val temperature: Double,
    val convectionCoefficient: Double,
)
