package pl.flashrow.data.model

data class CoolingEnvironmentDto(
    val coolingPlaceType: String,
    val name: String,
    val temperature: Double,
    val convectionCoefficient: Double,
)
