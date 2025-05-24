package pl.flashrow.data.model

data class BeverageTypeDto(
    val resourceId: Int,
    val nameResourceId: Int,
    val alcoholPercentage: Float,
    val specificHeat: Float,
    val density: Float,
)
