package com.aayar94.earthquakes.model

import android.graphics.Color
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "earthquakes")
data class EarthquakeModel(

    @SerializedName("mag")
    val magnitude: Double,

    @SerializedName("title")
    val name: String,

    @SerializedName("date")
    val dateAndTime: String,

    @SerializedName("lat")
    val lat: Double?,

    @SerializedName("lng")
    val lng: Double?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    val magnitudeColor: Int
        get() = when (magnitude) {
            in 0.0..2.9 -> Color.parseColor("#3ff10c")
            in 3.0..6.9 -> Color.parseColor("#efc30e")
            in 7.0..9.9 -> Color.parseColor("#f10c0c")
            else -> Color.WHITE
        }
    val magnitudeColorLight: Int
        get() = when (magnitude) {
            in 0.0..2.9 -> Color.parseColor("#defede")
            in 3.0..6.9 -> Color.parseColor("#fdf7dc")
            in 7.0..9.9 -> Color.parseColor("#fde9e9")
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
