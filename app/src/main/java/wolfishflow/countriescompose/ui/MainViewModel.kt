package wolfishflow.countriescompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wolfishflow.countriescompose.api.CountriesService
import wolfishflow.countriescompose.api.models.Country
import wolfishflow.countriescompose.network.NetworkClient

//TODO add Usecase class
class MainViewModel : ViewModel() {

    private suspend fun getCanada() : List<Country> {
        val retrofit = NetworkClient().getRetrofit()
        val service = retrofit.create(CountriesService::class.java)
        return service.getCanada()
    }

    fun retrieveCanada() {
        viewModelScope.launch(Dispatchers.IO) {
            getCanada()
        }
    }

}