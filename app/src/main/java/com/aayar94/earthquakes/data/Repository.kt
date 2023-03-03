package com.aayar94.earthquakes.data

import com.aayar94.earthquakes.model.EarthquakeModel
import javax.inject.Inject

class Repository @Inject constructor(
    val earthquakeService : EarthquakeService
){
    suspend fun getEarthquakes(): List<EarthquakeModel> {
        return earthquakeService.getEarthquakes().body()?.data.orEmpty()
    }
}