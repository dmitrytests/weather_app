package com.kanemara.weather.weatherapp.presentation.ui;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kanemara.weather.weatherapp.WeatherApplication;
import com.kanemara.weather.weatherapp.domain.interactors.base.UpdateInteractor;
import com.kanemara.weather.weatherapp.domain.interactors.impl.UpdateWeatherInteractor;
import com.kanemara.weather.weatherapp.domain.model.WeatherData;
import com.kanemara.weather.weatherapp.domain.repository.impl.WeatherRepository;
import com.kanemara.weather.weatherapp.presentation.contracts.MainScreenContracts;
import com.kanemara.weather.weatherapp.presentation.di.components.DaggerServiceComponent;
import com.kanemara.weather.weatherapp.presentation.di.components.ServiceComponent;
import com.kanemara.weather.weatherapp.presentation.di.modules.ServiceModule;

import javax.inject.Inject;

/**
 * Created by dima on 4/3/18.
 */

public class WeatherUpdateService extends Service implements MainScreenContracts.RequestExecutorService{
    public static final int DEFAULT_PERIOD_MILLIS=5000;
    private final IBinder localBinder = new LocalBinder();
    private MainScreenContracts.RequestExecutorService.Callback callback;
    private ServiceComponent component;
    @Inject
    WeatherRepository repository;
    @Inject
    UpdateWeatherInteractor interactor;

    private Handler periodicWorker = new Handler();
    private Runnable worker = new Runnable() {
        @Override
        public void run() {
            interactor.execute(new UpdateInteractor.Callback<WeatherData>(){
                @Override
                public void onLoaded(WeatherData result) {
                    callback.onUpdate(result);
                    periodicWorker.postDelayed(worker, DEFAULT_PERIOD_MILLIS);
                }

                @Override
                public void onFailed(Throwable throwable) {
                    callback.onError(throwable);
                }
            });
        }
    };

    private void setupComponent() {
        component = DaggerServiceComponent.builder().appComponent(WeatherApplication.get(this).getComponent()).serviceModule(new ServiceModule(this)).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
        component.inject(this);
    }

    public void registerForUpdates(Callback callback){
        this.callback = callback;
        doStopUpdates();
        doStartUpdates();
    }

    public void unregisterForUpdates(){
        doStopUpdates();
        callback=null;
    }

    private void doStartUpdates(){
        periodicWorker.post(worker);
    }

    private void doStopUpdates(){
        periodicWorker.removeCallbacks(worker);
    }


    @Override
    public void onDestroy() {
        doStopUpdates();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LocalBinder extends Binder{
        WeatherUpdateService getService(){
            return WeatherUpdateService.this;
        }
    }
}
