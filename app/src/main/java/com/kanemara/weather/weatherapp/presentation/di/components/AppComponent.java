package com.kanemara.weather.weatherapp.presentation.di.components;

import com.kanemara.weather.weatherapp.WeatherApplication;
import com.kanemara.weather.weatherapp.domain.repository.impl.WeatherRepository;
import com.kanemara.weather.weatherapp.presentation.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dima on 4/3/18.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(WeatherApplication application);
    WeatherRepository getRepository();
}
