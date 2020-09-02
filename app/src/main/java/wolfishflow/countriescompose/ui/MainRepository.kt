package wolfishflow.countriescompose.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import wolfishflow.countriescompose.data.CountriesService
import wolfishflow.countriescompose.data.models.Country

class MainRepository(
    private val service: CountriesService,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getAllCountries(): Flow<List<Country>> {
        return flow {
            emit(service.getAllCountries())
        }.flowOn(coroutineDispatcher)
    }

}