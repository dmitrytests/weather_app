package com.kanemara.weather.weatherapp.presentation.data;

import com.kanemara.weather.weatherapp.domain.model.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dima on 4/2/18.
 */

public class WeatherUIData {
    public String cityName;
    public String forecast;
    public String windInfo;
    public String tempInfo;
    public long time;
    public String timeFormatted;
    public String updateTimeFormatted;


    public WeatherUIData(WeatherData source) {
        cityName = source.getName();
        time = source.getDt()*1000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        timeFormatted = simpleDateFormat.format(new Date(time));
        updateTimeFormatted = simpleDateFormat.format(new Date());
        tempInfo = String.valueOf(source.getMainItem().temperature);
        windInfo = String.valueOf(source.getWindItem().speed);
        if(source.getWeatherItems()!=null && !source.getWeatherItems().isEmpty()){
            WeatherData.WeatherItem weatherItem = source.getWeatherItems().get(0);
            forecast = weatherItem.main + " - " + weatherItem.description;
        }else {
            forecast = "";
        }
    }
}
