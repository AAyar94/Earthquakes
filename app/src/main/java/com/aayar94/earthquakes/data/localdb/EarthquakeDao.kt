package com.aayar94.earthquakes.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.earthquakes.model.EarthquakeModel

@Dao
interface EarthquakeDao {
    @Query("SELECT * FROM earthquakes")
    suspend fun getEarthquakes(): List<EarthquakeModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarthquakes(list: List<EarthquakeModel>)

    @Query("DELETE FROM earthquakes")
    suspend fun deleteDbList()

    @Query("SELECT * FROM earthquakes ORDER BY magnitude DESC")
    suspend fun sortHighMag(): List<EarthquakeModel>

    @Query("SELECT * FROM earthquakes ORDER BY magnitude ASC")
    suspend fun sortLowMag(): List<EarthquakeModel>

    @Query("SELECT * FROM earthquakes WHERE name LIKE '%' || :query || '%'")
    suspend fun searchEarthquakes(query: String?): List<EarthquakeModel>
}