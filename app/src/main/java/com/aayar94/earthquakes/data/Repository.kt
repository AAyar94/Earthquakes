package com.aayar94.earthquakes.data

import com.aayar94.earthquakes.data.localdb.EarthquakeDao
import com.aayar94.earthquakes.model.EarthquakeModel
import javax.inject.Inject

class Repository @Inject constructor(
    val earthquakeService: EarthquakeService,
    val earthquakeDao: EarthquakeDao
) {
    suspend fun getEarthquakesFromRemote(): List<EarthquakeModel> {
        return earthquakeService.getEarthquakes().body()?.data.orEmpty()
    }

    suspend fun getEarthquakesFromLocal(): List<EarthquakeModel> {
        return earthquakeDao.getEarthquakes()
    }

    suspend fun insertToDb(list: List<EarthquakeModel>) {
        earthquakeDao.insertEarthquakes(list)
    }

    suspend fun deleteDbList(){
        earthquakeDao.deleteDbList()
    }

}