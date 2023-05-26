package com.example.finalproject.trips;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("current.json")
    Call<WeatherData> getCurrentWeather(@Query("key") String apiKey, @Query("q") String location);
}
