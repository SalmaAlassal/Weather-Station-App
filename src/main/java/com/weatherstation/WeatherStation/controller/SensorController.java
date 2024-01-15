package com.weatherstation.WeatherStation.controller;

import com.weatherstation.WeatherStation.model.SensorData;
import com.weatherstation.WeatherStation.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/weather-api")
public class SensorController {
    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping
    public String index(Model model) {
        SensorData latestSensorData = sensorDataService.getLatestSensorData();
        model.addAttribute("latestSensorData", latestSensorData);

        // Get the logs
        List<SensorData> logs = sensorDataService.getData();
        model.addAttribute("logs", logs);

        // Retrieve the threshold value
        float temperatureThreshold = latestSensorData.getTemperatureThreshold();
        float humidityThreshold = latestSensorData.getHumidityThreshold();
        float pressureThreshold = latestSensorData.getPressureThreshold();
        float altitudeThreshold = latestSensorData.getAltitudeThreshold();

        // Add the threshold values to the model
        model.addAttribute("temperatureThreshold", temperatureThreshold);
        model.addAttribute("humidityThreshold", humidityThreshold);
        model.addAttribute("pressureThreshold", pressureThreshold);
        model.addAttribute("altitudeThreshold", altitudeThreshold);

        // Retrieve the email
        String email = latestSensorData.getEmail();

        // Add the email to the model
        model.addAttribute("email", email);

        return "index";
    }

    @PostMapping
    ResponseEntity<String> addData(@RequestBody SensorData sensorData) {
        // System.out.println("Received data: " + sensorData);
        sensorDataService.addData(sensorData);
        return ResponseEntity.ok("Data added successfully");
    }
}
