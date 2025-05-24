package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.BeverageTypeDto
import pl.flashrow.dcc.core.model.BeverageType

fun mapDrinkTypeDtoToDrinkType(drinkType: BeverageTypeDto): BeverageType = BeverageType(
    iconResourceId = drinkType.resourceId,
    nameResourceId = drinkType.nameResourceId,
    alcoholPercentage = drinkType.alcoholPercentage,
    specificHeat = drinkType.specificHeat,
    density = drinkType.density,
)
