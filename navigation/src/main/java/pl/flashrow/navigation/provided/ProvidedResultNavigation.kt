package pl.flashrow.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.flashrow.calculator.presentation.results.ResultNavigation

internal class ProvidedResultNavigation (navigator: DestinationsNavigator) : ResultNavigation, DestinationsNavigator by navigator{
    override fun navigateBack() {
        popBackStack()
    }
}