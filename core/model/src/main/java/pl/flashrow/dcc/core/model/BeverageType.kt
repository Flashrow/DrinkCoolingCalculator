package pl.flashrow.dcc.core.model

data class BeverageType(
    val iconResourceId: Int,
    val nameResourceId: Int,
    val alcoholPercentage: Float,
    val specificHeat: Float,
    val density: Float,
)
