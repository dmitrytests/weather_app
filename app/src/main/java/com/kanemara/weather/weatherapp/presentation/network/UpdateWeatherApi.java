package com.kanemara.weather.weatherapp.presentation.network;

import com.kanemara.weather.weatherapp.domain.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dima on 4/2/18.
 */

public interface UpdateWeatherApi {
    @GET("weather")
    Call<WeatherData> fetchUpdate(@Query("units") String units, @Query("id") int cityId, @Query("APPID") String apiKey);
}
