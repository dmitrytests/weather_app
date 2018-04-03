package com.kanemara.weather.weatherapp.presentation.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kanemara.weather.weatherapp.R;
import com.kanemara.weather.weatherapp.presentation.contracts.MainScreenContracts;
import com.kanemara.weather.weatherapp.presentation.data.WeatherUIData;
import com.kanemara.weather.weatherapp.presentation.di.components.DaggerMainViewComponent;
import com.kanemara.weather.weatherapp.presentation.di.components.MainViewComponent;
import com.kanemara.weather.weatherapp.presentation.di.modules.MainViewModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContracts.View {
    private TextView forecast;
    private TextView cityName;
    private TextView temperature;
    private TextView windValue;
    private TextView updateTime;
    private TextView updateUiTime;

    private MainViewComponent component;
    @Inject
     MainScreenContracts.Presenter presenter;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            presenter.onBoundToService(((WeatherUpdateService.LocalBinder)service).getService());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            presenter.onUnboundToService();
        }
    };

    protected void setupComponent() {
        component = DaggerMainViewComponent.builder().mainViewModule(new MainViewModule(this)).build();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent();
        component.inject(this);
        setContentView(R.layout.activity_main);
        forecast = (TextView) findViewById(R.id.forecast);
        cityName = (TextView) findViewById(R.id.city_name);
        temperature = (TextView) findViewById(R.id.temperature_value);
        windValue = (TextView) findViewById(R.id.wind_value);
        updateTime = (TextView) findViewById(R.id.update_value);
        updateUiTime = (TextView) findViewById(R.id.update_ui_value);
    }



    @Override
    public void updateData(WeatherUIData data) {
        cityName.setText(data.cityName);
        forecast.setText(data.forecast);
        temperature.setText(data.tempInfo);
        windValue.setText(data.windInfo);
        updateTime.setText(data.timeFormatted);
        updateUiTime.setText(data.updateTimeFormatted);
    }

    @Override
    public void showError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
       doStartService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onAboutUnbound();
        unbindService(serviceConnection);
    }

    @Override
    protected void onDestroy() {
        if(isFinishing()) {
            doStopService();
        }
        super.onDestroy();
    }

    private void doStopService(){
        Intent serviceIntent = new Intent(this, WeatherUpdateService.class);
        stopService(serviceIntent);
    }

    private void doStartService(){
        Intent intent = new Intent(this, WeatherUpdateService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
