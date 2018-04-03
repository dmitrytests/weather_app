package com.kanemara.weather.weatherapp.presentation.di.components;

import com.kanemara.weather.weatherapp.domain.interactors.impl.UpdateWeatherInteractor;
import com.kanemara.weather.weatherapp.presentation.di.PerActivity;
import com.kanemara.weather.weatherapp.presentation.di.modules.ServiceModule;
import com.kanemara.weather.weatherapp.presentation.ui.WeatherUpdateService;

import dagger.Component;

/**
 * Created by dima on 4/3/18.
 */

@PerActivity
@Component(modules= ServiceModule.class, dependencies = AppComponent.class)
public interface ServiceComponent {
    void inject(WeatherUpdateService service);
   // WeatherRepository getRepository();
    UpdateWeatherInteractor getInteractor();
}
