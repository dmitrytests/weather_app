package com.kanemara.weather.weatherapp.domain.repository;

/**
 * Created by dima on 4/2/18.
 */

public interface Repository<T> {

    interface DataLoadedCallback<T>{
        void onDataLoaded(T data);
        void onLoadingError(Throwable t);
    }
    void fetchData(DataLoadedCallback<T> callback);
    long getLastUpdateTime();
}

