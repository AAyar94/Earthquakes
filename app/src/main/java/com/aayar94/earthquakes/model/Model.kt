package com.aayar94.earthquakes.model

import android.graphics.Color
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "earthquakes")
data class EarthquakeModel(

    @PrimaryKey
    @SerializedName("earthquake_id")
    val uuid: String,

    @SerializedName("mag")
    val magnitude: Double,

    @SerializedName("title")
    val name: String,

    @SerializedName("date")
    val dateAndTime: String,

    @SerializedName("geojson")
    val geoJson: GeoJson,

    @SerializedName("depth")
    val depth: Double?

) : Parcelable {

    val magnitudeColor: Int
        get() = when (magnitude) {
            in 0.0..2.9 -> Color.parseColor("#97ff97")
            in 3.0..6.9 -> Color.parseColor("#fffd99")
            in 7.0..9.9 -> Color.parseColor("#ffa899")
            else -> Color.WHITE
        }
    val magnitudeColorLight: Int
        get() = when (magnitude) {
            in 0.0..2.9 -> Color.parseColor("#b1ffb1")
            in 3.0..6.9 -> Color.parseColor("#fffeb3")
            in 7.0..9.9 -> Color.parseColor("#ffbeb3")
            else -> Color.WHITE
        }
    val magnitudeText: String
        get() = String.format("%.1f", magnitude)

    val date: String
        get() = dateAndTime.split(" ")[0]

    val time: String
        get() = dateAndTime.split(" ")[1]

    /*    val latText: String
        get() = String.format("%.4f",lat)
        val lngText: String
        get() = String.format("%4f",lng)*/


}

@Parcelize
class GeoJson(
    val coordinates: List<Double>
) : Parcelable
