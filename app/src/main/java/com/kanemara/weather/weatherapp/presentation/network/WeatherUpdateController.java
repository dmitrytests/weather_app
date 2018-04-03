package com.kanemara.weather.weatherapp.presentation.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanemara.weather.weatherapp.domain.model.WeatherData;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dima on 4/2/18.
 */

public class WeatherUpdateController implements retrofit2.Callback<WeatherData> {
    public interface WeatherCallback {
        void onResponse(@NonNull WeatherData data);
        void onError(Throwable t);
    }
    public static final String TAG = WeatherUpdateController.class.getCanonicalName();
    private WeatherCallback callback;
    private String baseUrl;
    private String apiKey;

    public WeatherUpdateController(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public void start(String units, int cityId, @NonNull WeatherCallback callback){
        this.callback = callback;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        UpdateWeatherApi apiExecutor = retrofit.create(UpdateWeatherApi.class);
        Call<WeatherData> call = apiExecutor.fetchUpdate(units, cityId, apiKey);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
        if(response.isSuccessful()){
            callback.onResponse(response.body());
        }else{
            Log.d(TAG, response.code() + " : "+response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<WeatherData> call, Throwable t) {
        callback.onError(t);
    }
}
