package wolfishflow.countriescompose.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wolfishflow.countriescompose.data.CountriesService
import wolfishflow.countriescompose.data.models.Country
import wolfishflow.countriescompose.data.NetworkClient

//TODO add contructor for repo class
class MainViewModel : ViewModel() {
    private val _countriesList = MutableLiveData<List<Country>>()
    val countriesList: LiveData<List<Country>> get() = _countriesList

    fun retrieveCanada() {
        viewModelScope.launch {
            val retrofit = NetworkClient().getRetrofit()
            val service = retrofit.create(CountriesService::class.java)
            val repo = MainRepository(service, Dispatchers.IO)

            repo.getAllCountries()
                .collect { countries ->
                _countriesList.value = countries
            }
        }
    }

}