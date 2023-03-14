package com.aayar94.earthquakes.data.localdb

import androidx.room.TypeConverter
import com.aayar94.earthquakes.model.GeoJson


class GeoLocationTypeConverter {

    @TypeConverter
    fun fromList(geoJson: GeoJson): String {
        return "${geoJson.coordinates[0]},${geoJson.coordinates[1]}"
    }

    @TypeConverter
    fun fromString(string: String): GeoJson {
        val double = string.split(",").map { it.toDouble() }
        return GeoJson(
            double
        )
    }
}