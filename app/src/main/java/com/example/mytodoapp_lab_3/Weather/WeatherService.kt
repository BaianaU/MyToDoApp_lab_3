package com.example.mytodoapp_lab_3.Weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}
