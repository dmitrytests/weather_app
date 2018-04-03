package com.kanemara.weather.weatherapp.presentation.di.modules;

import com.kanemara.weather.weatherapp.WeatherApplication;
import com.kanemara.weather.weatherapp.domain.repository.impl.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dima on 4/3/18.
 */


@Module
public class AppModule {
    private WeatherApplication application;

    public AppModule(WeatherApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    WeatherRepository getRepository(){
        return new WeatherRepository();
    }


    @Provides @Singleton
    public WeatherApplication provideApplication() {
        return application;
    }


}
