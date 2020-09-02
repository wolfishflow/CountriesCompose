package wolfishflow.countriescompose.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import wolfishflow.countriescompose.data.CountriesService
import wolfishflow.countriescompose.data.models.Country
import javax.inject.Inject

class MainRepository @Inject constructor (
    private val service: CountriesService
) {

    fun getAllCountries(): Flow<List<Country>> {
        return flow {
            emit(service.getAllCountries())
        }.flowOn(Dispatchers.IO)
        //todo need to change the dispatcher to var for testing
    }

}