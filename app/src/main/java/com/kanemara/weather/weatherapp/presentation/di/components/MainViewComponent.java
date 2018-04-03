package com.kanemara.weather.weatherapp.presentation.di.components;

import com.kanemara.weather.weatherapp.presentation.contracts.MainScreenContracts;
import com.kanemara.weather.weatherapp.presentation.di.PerActivity;
import com.kanemara.weather.weatherapp.presentation.di.modules.MainViewModule;
import com.kanemara.weather.weatherapp.presentation.ui.MainActivity;

import dagger.Component;

/**
 * Created by dima on 4/3/18.
 */

@PerActivity
@Component(modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity mainActivity);
    MainScreenContracts.Presenter getPresenter();
}
