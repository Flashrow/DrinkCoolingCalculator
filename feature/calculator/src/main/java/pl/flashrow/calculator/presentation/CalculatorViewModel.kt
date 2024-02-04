package pl.flashrow.calculator.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.flashrow.domain.GetDrinkTypesUseCase
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getDrinkTypesUseCase: GetDrinkTypesUseCase
) : ViewModel() {

}