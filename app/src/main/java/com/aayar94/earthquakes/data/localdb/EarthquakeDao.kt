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


}