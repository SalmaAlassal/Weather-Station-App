package com.weatherstation.WeatherStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weatherstation.WeatherStation.model.SensorData;
import com.weatherstation.WeatherStation.repository.SensorDataRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Service
public class SensorDataService {
    @Autowired
    private SensorDataRepository sensorDataRepository;

    public void addData(SensorData sensorData) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        sensorData.setTimestamp(formattedDate);

        // Add the same threshold values to the model
        sensorData.setTemperatureThreshold(getLatestSensorData().getTemperatureThreshold());
        sensorData.setHumidityThreshold(getLatestSensorData().getHumidityThreshold());
        sensorData.setPressureThreshold(getLatestSensorData().getPressureThreshold());
        sensorData.setAltitudeThreshold(getLatestSensorData().getAltitudeThreshold());
        sensorData.setEmail(getLatestSensorData().getEmail());
        
        sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getData() {
        return sensorDataRepository.findAll();
    }

    public SensorData getLatestSensorData() {
        return sensorDataRepository.findTopByOrderByIdDesc();
    }

    public void updateThresholds(float temperatureThreshold, float humidityThreshold, float pressureThreshold,
            float altitudeThreshold) {
        SensorData sensorData = getLatestSensorData();
        sensorData.setTemperatureThreshold(temperatureThreshold);
        sensorData.setHumidityThreshold(humidityThreshold);
        sensorData.setPressureThreshold(pressureThreshold);
        sensorData.setAltitudeThreshold(altitudeThreshold);
        sensorDataRepository.save(sensorData);
    }
    
    public void updateEmail(String email) {
        SensorData sensorData = getLatestSensorData();
        sensorData.setEmail(email);
        sensorDataRepository.save(sensorData);
    }
}