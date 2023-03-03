package com.aayar94.earthquakes.model

import android.graphics.ColorSpace
import com.google.gson.annotations.SerializedName


    data class EarthquakeWrapper(
        @SerializedName("result")
        val data: List<EarthquakeModel>
    )
