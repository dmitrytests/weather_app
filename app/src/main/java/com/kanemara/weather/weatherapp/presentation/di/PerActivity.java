package com.kanemara.weather.weatherapp.presentation.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by dima on 4/3/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}