package com.kanemara.weather.weatherapp.presentation.contracts;

import com.kanemara.weather.weatherapp.domain.model.WeatherData;
import com.kanemara.weather.weatherapp.presentation.data.WeatherUIData;

/**
 * Created by dima on 4/2/18.
 */

public interface MainScreenContracts {
    interface  View{
        void updateData(WeatherUIData data);
        void showError(String text);
    }

    interface Presenter{
        void onBoundToService(RequestExecutorService service);
        void onAboutUnbound();
        void onUnboundToService();
    }

    interface RequestExecutorService{
        interface Callback{
            void onUpdate(WeatherData data);
            void onError(Throwable t);
        }
        void registerForUpdates(Callback callback);
        void unregisterForUpdates();
    }
}
