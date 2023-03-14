package com.aayar94.earthquakes.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aayar94.earthquakes.model.EarthquakeModel
import com.aayar94.earthquakes.model.GeoJson

@Database(entities = [EarthquakeModel::class], version = 1)
@TypeConverters(GeoLocationTypeConverter::class)
abstract class EarthquakeDatabase : RoomDatabase() {
    abstract fun getEarthquakeDao(): EarthquakeDao
}