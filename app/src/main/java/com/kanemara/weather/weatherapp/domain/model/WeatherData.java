package com.kanemara.weather.weatherapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dima on 4/2/18.
 */

public class WeatherData {
    @SerializedName("weather")
    List<WeatherItem> weatherItems;
    @SerializedName("visibility")
    int visibility;
    @SerializedName("main")
    MainItem mainItem;
    @SerializedName("wind")
    WindItem windItem;
    @SerializedName("dt")
    long dt;
    @SerializedName("name")
    String name;

    public long getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public List<WeatherItem> getWeatherItems() {
        return weatherItems;
    }

    public MainItem getMainItem() {
        return mainItem;
    }

    public WindItem getWindItem() {
        return windItem;
    }

    public static class WeatherItem{
        @SerializedName("id")
        int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
    }

    public static class MainItem{
        @SerializedName("temp")
        public float temperature;
        @SerializedName("pressure")
        int  pressure;
        @SerializedName("humidity")
        int humidity;
    }

    public static class WindItem{
        @SerializedName("speed")
        public int speed;
        @SerializedName("deg")
        int  deg;
    }



}

//{
//        "coord": {
//        "lon": 37.62,
//        "lat": 55.75
//        },
//        "weather": [{
//        "id": 804,
//        "main": "Clouds",
//        "description": "overcast clouds",
//        "icon": "04n"
//        }],
//        "base": "stations",
//        "main": {
//        "temp": 2.25,
//        "pressure": 1005,
//        "humidity": 97,
//        "temp_min": 2,
//        "temp_max": 3
//        },
//        "visibility": 10000,
//        "wind": {
//        "speed": 4,
//        "deg": 160
//        },
//        "clouds": {
//        "all": 90
//        },
//        "dt": 1522692000,
//        "sys": {
//        "type": 1,
//        "id": 7325,
//        "message": 0.0029,
//        "country": "RU",
//        "sunrise": 1522637784,
//        "sunset": 1522685437
//        },
//        "id": 524901,
//        "name": "Moscow",
//        "cod": 200
//        }

