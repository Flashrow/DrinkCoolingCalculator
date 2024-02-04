package pl.flashrow.data.repository

import pl.flashrow.data.model.DrinkTypeDto

interface CalculatorRepository {
    fun getDrinkTypes() : List<DrinkTypeDto>
}