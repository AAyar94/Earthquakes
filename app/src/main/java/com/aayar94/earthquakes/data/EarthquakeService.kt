package com.aayar94.earthquakes.data

import com.aayar94.earthquakes.model.EarthquakeWrapper
import retrofit2.Response
import retrofit2.http.GET

interface EarthquakeService {

    @GET("/deprem/kandilli/live")
    suspend fun getEarthquakes(): Response<EarthquakeWrapper>
}