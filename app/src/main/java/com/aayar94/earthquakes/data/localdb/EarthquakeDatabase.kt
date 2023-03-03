package com.aayar94.earthquakes.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aayar94.earthquakes.model.EarthquakeModel

@Database(entities = [EarthquakeModel::class], version = 1)
abstract class EarthquakeDatabase : RoomDatabase() {
    abstract fun getEarthquakeDao(): EarthquakeDao
}