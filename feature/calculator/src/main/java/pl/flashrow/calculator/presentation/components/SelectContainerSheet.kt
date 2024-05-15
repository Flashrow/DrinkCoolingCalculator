package pl.flashrow.calculator.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.flashrow.dcc.core.enum.Material
import pl.flashrow.dcc.core.model.ContainerType
import pl.flashrow.designsystem.Dimens

@Composable
fun SelectContainerSheet(
    onSelect: (ContainerType) -> Unit,
    closeModalSheet: () -> Unit,
    containerTypes: List<ContainerType>
) {
    var selectedContainerType by remember { mutableStateOf<ContainerType?>(null) }
    Column(
        modifier = Modifier.padding(horizontal = Dimens.baseMargin)
    ) {
        Text(
            text = "Wybierz rodzaj pojemnika",
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = "Jeżeli na liście nie ma dokładnie takiego samego \u2028opakowania wybierz te, które wymiarami jest najbardziej\n" +
                    "podobne",
            modifier = Modifier.padding(top = Dimens.baseMargin),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.padding(top = Dimens.smallMargin))
        containerTypes.forEach { containerType ->
            ContainerTypeRow(
                containerType = containerType,
                selectedContainerType = selectedContainerType,
                onClick = {
                    selectedContainerType = it
                }
            )
        }
        Spacer(modifier = Modifier.padding(top = Dimens.smallMargin))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    closeModalSheet()
                }) {
                Text(text = "Anuluj")

            }
            TextButton(
                onClick = {
                    if (selectedContainerType != null)
                        onSelect(selectedContainerType!!)
                    closeModalSheet()
                }) {
                Text(text = "Dalej")
            }
        }
    }
}

@Composable
private fun ContainerTypeRow(
    containerType: ContainerType,
    selectedContainerType: ContainerType?,
    onClick: (ContainerType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(containerType)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selectedContainerType == containerType,
            onClick = {
                onClick(containerType)
            }
        )
        Text(
            text = containerType.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = Dimens.baseMargin)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SelectContainerSheetPreview() {
    SelectContainerSheet(
        onSelect = {},
        closeModalSheet = {},
        containerTypes = listOf(
            ContainerType(
                name = "Bottle",
                capacityMl = 500,
                material = Material.PLASTIC,
            ),
            ContainerType(
                name = "Can",
                capacityMl = 330,
                material = Material.METAL,
            ),
            ContainerType(
                name = "Can",
                capacityMl = 330,
                material = Material.METAL,
            )
        ),
    )
}