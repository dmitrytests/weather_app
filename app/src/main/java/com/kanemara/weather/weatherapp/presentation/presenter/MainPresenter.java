package com.kanemara.weather.weatherapp.presentation.presenter;

import com.kanemara.weather.weatherapp.domain.model.WeatherData;
import com.kanemara.weather.weatherapp.presentation.contracts.MainScreenContracts;
import com.kanemara.weather.weatherapp.presentation.data.WeatherUIData;

/**
 * Created by dima on 4/2/18.
 */

public class MainPresenter implements MainScreenContracts.Presenter {

    private MainScreenContracts.View view;
    private boolean isBound;
    private MainScreenContracts.RequestExecutorService service;

    public MainPresenter(MainScreenContracts.View view) {
        this.view = view;
    }

    @Override
    public void onBoundToService(MainScreenContracts.RequestExecutorService service) {
        this.service = service;
        isBound=true;
        service.registerForUpdates(new MainScreenContracts.RequestExecutorService.Callback() {
            @Override
            public void onUpdate(WeatherData result) {
                view.updateData(new WeatherUIData(result));
            }

            @Override
            public void onError(Throwable throwable) {
                view.showError(throwable.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onAboutUnbound() {
        if(isBound) {
            service.unregisterForUpdates();
        }
    }

    @Override
    public void onUnboundToService() {
        if(isBound) {
            service = null;
            isBound = false;
        }
    }


}
