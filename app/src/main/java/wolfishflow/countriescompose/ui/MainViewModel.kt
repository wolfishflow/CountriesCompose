package wolfishflow.countriescompose.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wolfishflow.countriescompose.data.models.Country


class MainViewModel @ViewModelInject
constructor(private val repository: MainRepository) : ViewModel() {
    private val _countriesList = MutableLiveData<List<Country>>()
    val countriesList: LiveData<List<Country>> get() = _countriesList

    fun retrieveCanada() {
        viewModelScope.launch {
            repository.getAllCountries()
                .collect { countries ->
                    _countriesList.value = countries
                }
        }
    }
}