package wolfishflow.countriescompose.ui

import androidx.ui.tooling.preview.PreviewParameterProvider
import wolfishflow.countriescompose.data.models.Country
import wolfishflow.countriescompose.data.models.Currency

class CountryPreviewProvider : PreviewParameterProvider<List<Country>> {
    override val values: Sequence<List<Country>> = sequenceOf(listOf(CANADA))
    override val count: Int = 1

    companion object {
        val CANADA: Country = Country(
            name = "Canada",
            alpha2Code = "CA",
            alpha3Code = "CAN",
            capital = "Ottawa",
            region = "Americas",
            subregion = "Northern America",
            population = 36155487,
            demonym = "Canadian",
            currencies = listOf(
                Currency(
                    code = "CAD",
                    name = "Canadian dollar",
                    symbol = "$"
                )
            ),
            flagImageUrl = "https://restcountries.eu/data/can.svg"
        )
    }
}
