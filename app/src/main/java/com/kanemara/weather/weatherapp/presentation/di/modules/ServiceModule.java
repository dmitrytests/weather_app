package com.kanemara.weather.weatherapp.presentation.di.modules;

import com.kanemara.weather.weatherapp.domain.interactors.impl.UpdateWeatherInteractor;
import com.kanemara.weather.weatherapp.domain.repository.impl.WeatherRepository;
import com.kanemara.weather.weatherapp.presentation.di.PerActivity;
import com.kanemara.weather.weatherapp.presentation.ui.WeatherUpdateService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dima on 4/3/18.
 */
@Module
public class ServiceModule {
    WeatherUpdateService service;

    public ServiceModule(WeatherUpdateService service) {
        this.service = service;
    }


    @Provides
    @PerActivity
    UpdateWeatherInteractor getUpdateWeatherInteractor(WeatherRepository repository){
        return new UpdateWeatherInteractor(repository);
    }
}
