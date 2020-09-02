package wolfishflow.countriescompose.ui

import wolfishflow.countriescompose.data.CountriesService
import javax.inject.Inject

class MainRepository @Inject constructor (
    private val service: CountriesService
) {

    suspend fun getAllCountries() = service.getAllCountries()
}