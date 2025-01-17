package pl.flashrow.data.repository

import pl.flashrow.data.model.ContainerTypeDto
import pl.flashrow.data.model.CoolingPlaceDto
import pl.flashrow.data.model.DrinkTypeDto
import pl.flashrow.dcc.core.resources.R
import javax.inject.Inject

class CalculatorRepositoryImpl @Inject constructor() : CalculatorRepository {
    override fun getDrinkTypes(): List<DrinkTypeDto> {
        return listOf(
            DrinkTypeDto(
                resourceId = R.drawable.beer_icon,
                name = "Beer",
                alcoholPercentage = 0.04f
            ),
            DrinkTypeDto(
                resourceId = R.drawable.spirit_icon,
                name = "Spirit",
                alcoholPercentage = 0.40f
            ),
            DrinkTypeDto(
                resourceId = R.drawable.wine_icon,
                name = "Wine",
                alcoholPercentage = 0.13f
            ),
            DrinkTypeDto(
                resourceId = R.drawable.tea_icon,
                name = "Tea",
                alcoholPercentage = 0f
            ),
            DrinkTypeDto(
                resourceId = R.drawable.soft_drink_icon,
                name = "Soft drink",
                alcoholPercentage = 0f
            ),
        );
    }

    override fun getContainerTypes(): List<ContainerTypeDto> {
        return listOf(
            ContainerTypeDto(
                name = "Szklana butelka 500ml",
                capacityMl = 500,
                material = "Glass",
            ),
            ContainerTypeDto(
                name = "Plastikowa butelka 500ml",
                capacityMl = 500,
                material = "Plastic",
            ),
            ContainerTypeDto(
                name = "Puszka 330ml",
                capacityMl = 330,
                material = "Metal",
            ),
            ContainerTypeDto(
                name = "Puszka 500ml",
                capacityMl = 500,
                material = "Metal",
            ),
        )
    }

    override fun getCoolingPlaces(): List<CoolingPlaceDto> {
        return listOf(
            CoolingPlaceDto(
                coolingPlaceType = "FRIDGE",
                name = "Lodówka",
                temperature = 4
            ),
            CoolingPlaceDto(
                coolingPlaceType = "FREEZER",
                name = "Zamrażarka",
                temperature = -18
            ),
        )
    }
}