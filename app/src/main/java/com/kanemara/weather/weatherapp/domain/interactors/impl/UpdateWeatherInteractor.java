package com.kanemara.weather.weatherapp.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.kanemara.weather.weatherapp.domain.interactors.base.UpdateInteractor;
import com.kanemara.weather.weatherapp.domain.model.WeatherData;
import com.kanemara.weather.weatherapp.domain.repository.Repository;

/**
 * Created by dima on 4/2/18.
 */

public class UpdateWeatherInteractor implements UpdateInteractor<WeatherData> {
    private Repository<WeatherData> repository;

    public UpdateWeatherInteractor(@NonNull Repository<WeatherData> repository) {
        this.repository = repository;
    }

    public void execute(final @NonNull Callback<WeatherData> callback) {
        repository.fetchData(new Repository.DataLoadedCallback<WeatherData>() {
            @Override
            public void onDataLoaded(WeatherData data) {
                callback.onLoaded(data);
            }

            @Override
            public void onLoadingError(Throwable t) {
                callback.onFailed(t);
            }
        });
    }
}
