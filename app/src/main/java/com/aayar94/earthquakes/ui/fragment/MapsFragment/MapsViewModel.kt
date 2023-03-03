package com.aayar94.earthquakes.ui.fragment.MapsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aayar94.earthquakes.data.Repository
import com.aayar94.earthquakes.model.EarthquakeModel
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val list  = MutableLiveData<EarthquakeModel>()

    
}