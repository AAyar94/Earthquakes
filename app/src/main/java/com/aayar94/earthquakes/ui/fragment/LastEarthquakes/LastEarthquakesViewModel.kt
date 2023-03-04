package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.earthquakes.data.Repository
import com.aayar94.earthquakes.model.EarthquakeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastEarthquakesViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var earthquakes = MutableLiveData<List<EarthquakeModel>>()

    fun getEarthquakes() {
        viewModelScope.launch {
            var result = repository.getEarthquakesFromLocal()
            if (result.isNullOrEmpty()) {
                result = repository.getEarthquakesFromRemote()
                repository.insertToDb(result)
            }
            earthquakes.postValue(result)
        }
    }


    fun refreshEarthquakes() {
        viewModelScope.launch {
            val result = repository.getEarthquakesFromRemote()
            repository.deleteDbList()
            repository.insertToDb(result)
            earthquakes.postValue(result)
        }
    }

    fun sortHighMag() {
        viewModelScope.launch {
            earthquakes.postValue(repository.sortHighMag())
        }
    }

    fun sortLowMag() {
        viewModelScope.launch {
            earthquakes.postValue(repository.sortLowMag())
        }
    }

    fun searchEarthquake(query: String?) {
        viewModelScope.launch {
            earthquakes.postValue(
                repository.searchEarthquakes(query)
            )
        }


    }
}