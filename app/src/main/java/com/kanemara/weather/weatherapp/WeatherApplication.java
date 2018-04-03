package com.kanemara.weather.weatherapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.kanemara.weather.weatherapp.presentation.di.components.AppComponent;
import com.kanemara.weather.weatherapp.presentation.di.components.DaggerAppComponent;
import com.kanemara.weather.weatherapp.presentation.di.modules.AppModule;
import com.kanemara.weather.weatherapp.presentation.ui.WeatherUpdateService;

/**
 * Created by dima on 4/3/18.
 */

public class WeatherApplication extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        startUpdateService();
    }


    public static WeatherApplication get(Context context){
        return (WeatherApplication) context.getApplicationContext();
    }


    private void initComponent(){
        component= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent() {
        return component;
    }

    private void startUpdateService(){
        Intent serviceIntent = new Intent(this, WeatherUpdateService.class);
        startService(serviceIntent);
    }
}
