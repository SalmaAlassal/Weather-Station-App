package com.weatherstation.WeatherStation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sensor_data")
@Data
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp;
    private float temperature;
    private float humidity;
    private float pressure;
    private float altitude;
    private float temperatureThreshold;
    private float humidityThreshold;
    private float pressureThreshold;
    private float altitudeThreshold;
    private String email;
}