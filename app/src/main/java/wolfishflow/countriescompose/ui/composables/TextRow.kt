package wolfishflow.countriescompose.ui.composables

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import wolfishflow.countriescompose.data.models.Country

@Composable
fun NameCapitalAndRegion(country: Country) {
    with(country) {
        Text(text = name, style = MaterialTheme.typography.h6)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Capital: ${if (capital.isNotEmpty()) capital else "None"}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Region: ${if (region.isNotEmpty()) region else "None"}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}