package com.kanemara.weather.weatherapp.domain.repository.impl;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.kanemara.weather.weatherapp.BuildConfig;
import com.kanemara.weather.weatherapp.domain.model.WeatherData;
import com.kanemara.weather.weatherapp.domain.repository.Repository;
import com.kanemara.weather.weatherapp.presentation.network.WeatherUpdateController;

/**
 * Created by dima on 4/2/18.
 */

public class WeatherRepository implements Repository<WeatherData> {


    private long lastUpdateTime;

    @Override
    public void fetchData(final DataLoadedCallback<WeatherData> callback) {
        WeatherUpdateController controller = new WeatherUpdateController(BuildConfig.BASE_URL, BuildConfig.API_KEY);
        controller.start("metric", BuildConfig.DEFAULT_CITY_ID, new WeatherUpdateController.WeatherCallback() {
            @Override
            public void onResponse(@NonNull WeatherData data) {
                lastUpdateTime= SystemClock.elapsedRealtime();
                callback.onDataLoaded(data);
            }

            @Override
            public void onError(Throwable t) {
                callback.onLoadingError(t);
            }
        });
    }

    @Override
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}
