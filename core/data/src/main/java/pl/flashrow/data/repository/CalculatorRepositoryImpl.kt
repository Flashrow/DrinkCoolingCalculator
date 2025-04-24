package pl.flashrow.data.repository

import pl.flashrow.data.model.BeverageTypeDto
import pl.flashrow.data.model.ContainerTypeDto
import pl.flashrow.data.model.CoolingEnvironmentDto
import pl.flashrow.dcc.core.resources.R
import javax.inject.Inject

class CalculatorRepositoryImpl @Inject constructor() : CalculatorRepository {
    override fun getDrinkTypes(): List<BeverageTypeDto> {
        return listOf(
            BeverageTypeDto(
                resourceId = R.drawable.beer_icon,
                name = "Beer",
                alcoholPercentage = 0.04f,
                specificHeat = 4000f,
                density = 1020f
            ),
            BeverageTypeDto(
                resourceId = R.drawable.spirit_icon,
                name = "Spirit",
                alcoholPercentage = 0.40f,
                specificHeat = 3990f,
                density = 949f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.wine_icon,
                name = "Wine",
                alcoholPercentage = 0.13f,
                specificHeat = 4100f,
                density = 982f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.tea_icon,
                name = "Tea",
                alcoholPercentage = 0f,
                specificHeat = 4184f,
                density = 1000f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.soft_drink_icon,
                name = "Soft drink",
                alcoholPercentage = 0f,
                specificHeat = 3850f,
                density = 1040f,
            ),
        );
    }

    override fun getContainerTypes(): List<ContainerTypeDto> {
        return listOf(
            ContainerTypeDto(
                name = "Szklana butelka 500ml",
                capacityMl = 500,
                material = "Glass",
                surfaceArea = 0.0607,
            ),
            ContainerTypeDto(
                name = "Plastikowa butelka 500ml",
                capacityMl = 500,
                material = "Plastic",
                surfaceArea = 0.0505,
            ),
            ContainerTypeDto(
                name = "Puszka 330ml",
                capacityMl = 330,
                material = "Metal",
                surfaceArea = 0.0307,
            ),
            ContainerTypeDto(
                name = "Puszka 500ml",
                capacityMl = 500,
                material = "Metal",
                surfaceArea = 0.0417,
            ),
        )
    }

    override fun getCoolingPlaces(): List<CoolingEnvironmentDto> {
        return listOf(
            CoolingEnvironmentDto(
                coolingPlaceType = "FRIDGE",
                name = "Lodówka",
                temperature = 4.0,
                convectionCoefficient = 5.0
            ),
            CoolingEnvironmentDto(
                coolingPlaceType = "FREEZER",
                name = "Zamrażarka",
                temperature = -18.0,
                convectionCoefficient = 5.0
            ),
        )
    }
}