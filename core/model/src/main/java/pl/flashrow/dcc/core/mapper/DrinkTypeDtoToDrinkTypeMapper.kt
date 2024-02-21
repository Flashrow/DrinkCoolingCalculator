package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.DrinkTypeDto
import pl.flashrow.dcc.core.model.DrinkType

fun mapDrinkTypeDtoToDrinkType(drinkType: DrinkTypeDto): DrinkType = DrinkType(
    resourceId = drinkType.resourceId,
    name = drinkType.name,
    alcoholPercentage = drinkType.alcoholPercentage
)
