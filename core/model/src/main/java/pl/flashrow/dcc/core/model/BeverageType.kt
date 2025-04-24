package pl.flashrow.dcc.core.model

data class BeverageType(
    val resourceId: Int,
    val name: String,
    val alcoholPercentage: Float,
    val specificHeat: Float,
    val density: Float,
)
