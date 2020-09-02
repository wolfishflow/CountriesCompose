package wolfishflow.countriescompose.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: String,
    val demonym: String,
    val currencies: Array<Currency>,
    @Json(name = "flag")
    val flagImageUrl: String
)