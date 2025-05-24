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
                nameResourceId = R.string.beer,
                alcoholPercentage = 0.04f,
                specificHeat = 4000f,
                density = 1020f
            ),
            BeverageTypeDto(
                resourceId = R.drawable.spirit_icon,
                nameResourceId = R.string.strong_alcohol,
                alcoholPercentage = 0.40f,
                specificHeat = 3990f,
                density = 949f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.wine_icon,
                nameResourceId = R.string.wine,
                alcoholPercentage = 0.13f,
                specificHeat = 4100f,
                density = 982f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.tea_icon,
                nameResourceId = R.string.tea,
                alcoholPercentage = 0f,
                specificHeat = 4184f,
                density = 1000f,
            ),
            BeverageTypeDto(
                resourceId = R.drawable.soft_drink_icon,
                nameResourceId = R.string.soft_drink,
                alcoholPercentage = 0f,
                specificHeat = 3850f,
                density = 1040f,
            ),
        );
    }

    override fun getContainerTypes(): List<ContainerTypeDto> {
        return listOf(
            ContainerTypeDto(
                nameResourceId = R.string.glass_bottle_500,
                capacity = 0.0005,
                material = "Glass",
                surfaceArea = 0.0607,
            ),
            ContainerTypeDto(
                nameResourceId = R.string.plastic_bottle_500,
                capacity = 0.0005,
                material = "Plastic",
                surfaceArea = 0.0505,
            ),
            ContainerTypeDto(
                nameResourceId = R.string.can_330,
                capacity = 0.00033,
                material = "Metal",
                surfaceArea = 0.0307,
            ),
            ContainerTypeDto(
                nameResourceId = R.string.can_500,
                capacity = 0.0005,
                material = "Metal",
                surfaceArea = 0.0417,
            ),
        )
    }

    override fun getCoolingPlaces(): List<CoolingEnvironmentDto> {
        return listOf(
            CoolingEnvironmentDto(
                coolingPlaceType = "FRIDGE",
                nameResourceId = R.string.fridge,
                temperature = 4.0,
                convectionCoefficient = 5.0
            ),
            CoolingEnvironmentDto(
                coolingPlaceType = "FREEZER",
                nameResourceId = R.string.freezer,
                temperature = -18.0,
                convectionCoefficient = 5.0
            ),
        )
    }
}