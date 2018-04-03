package com.kanemara.weather.weatherapp.presentation.di.modules;

import com.kanemara.weather.weatherapp.presentation.contracts.MainScreenContracts;
import com.kanemara.weather.weatherapp.presentation.di.PerActivity;
import com.kanemara.weather.weatherapp.presentation.presenter.MainPresenter;
import com.kanemara.weather.weatherapp.presentation.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dima on 4/3/18.
 */

@Module
public class MainViewModule {
     MainActivity mainActivity;

    public MainViewModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @PerActivity
    @Provides
    MainScreenContracts.Presenter providesMainPresenter() {
        return new MainPresenter(this.mainActivity);
    }
}
