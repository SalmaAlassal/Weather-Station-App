package com.weatherstation.WeatherStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weatherstation.WeatherStation.model.SensorData;


public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    SensorData findTopByOrderByIdDesc();
}