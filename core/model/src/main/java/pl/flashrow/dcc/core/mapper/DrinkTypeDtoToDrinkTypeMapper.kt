package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.BeverageTypeDto
import pl.flashrow.dcc.core.model.BeverageType

fun mapDrinkTypeDtoToDrinkType(drinkType: BeverageTypeDto): BeverageType = BeverageType(
    resourceId = drinkType.resourceId,
    name = drinkType.name,
    alcoholPercentage = drinkType.alcoholPercentage,
    specificHeat = drinkType.specificHeat,
    density = drinkType.density,
)
