package com.kanemara.weather.weatherapp.domain.interactors.base;

import android.support.annotation.NonNull;

/**
 * Created by dima on 4/2/18.
 */

public interface UpdateInteractor<T> {
    public interface Callback<T>{
        void onLoaded(T result);
        void onFailed(Throwable throwable);
    }

    void execute(@NonNull Callback<T> callback);
}
