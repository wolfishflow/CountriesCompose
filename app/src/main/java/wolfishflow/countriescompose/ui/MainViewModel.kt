package wolfishflow.countriescompose.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import wolfishflow.countriescompose.data.models.Country


class MainViewModel @ViewModelInject
constructor(private val repository: MainRepository) : ViewModel() {
    val countriesList: LiveData<List<Country>> = liveData {
        emit(repository.getAllCountries())
    }

}