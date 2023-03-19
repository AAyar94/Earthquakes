package com.aayar94.earthquakes.data

import android.util.Log
import com.aayar94.earthquakes.data.localdb.EarthquakeDao
import com.aayar94.earthquakes.model.EarthquakeModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val earthquakeService: EarthquakeService,
    private val earthquakeDao: EarthquakeDao
) {
    suspend fun getEarthquakesFromRemote(): List<EarthquakeModel> {
        return try {
            earthquakeService.getEarthquakes().body()?.data.orEmpty()
        } catch (e: Exception) {
            Log.e("RemoteError", e.message.toString())
            earthquakeDao.getEarthquakes()
        }

    }

    suspend fun getEarthquakesFromLocal(): List<EarthquakeModel> {
        return earthquakeDao.getEarthquakes()
    }

    suspend fun insertToDb(list: List<EarthquakeModel>) {
        earthquakeDao.insertEarthquakes(list)
    }

    suspend fun deleteDbList() {
        earthquakeDao.deleteDbList()
    }

    suspend fun sortHighMag(): List<EarthquakeModel> {
        return earthquakeDao.sortHighMag()
    }

    suspend fun sortLowMag(): List<EarthquakeModel> {
        return earthquakeDao.sortLowMag()
    }

    suspend fun searchEarthquakes(query: String?): List<EarthquakeModel> {
        return earthquakeDao.searchEarthquakes(query)
    }
}