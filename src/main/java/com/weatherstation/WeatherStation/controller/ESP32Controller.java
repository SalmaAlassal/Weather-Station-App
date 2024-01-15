package com.weatherstation.WeatherStation.controller;

import com.weatherstation.WeatherStation.service.ESP32CommunicationService;
import com.weatherstation.WeatherStation.service.SensorDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather-api")
public class ESP32Controller {
    @Autowired
    private ESP32CommunicationService esp32CommunicationService;

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping("/updateSettings")
    public String updateSettings(@RequestParam float newTemperatureThreshold,
            @RequestParam float newHumidityThreshold, @RequestParam float newPressureThreshold,
            @RequestParam float newAltitudeThreshold, @RequestParam String newEmail,
            Model model) {

        System.out.println("Received new settings: " + newTemperatureThreshold + ", " + newHumidityThreshold + ", " + newPressureThreshold + ", " + newAltitudeThreshold + ", " + newEmail);

        esp32CommunicationService.updateThresholds(newTemperatureThreshold, newHumidityThreshold, newPressureThreshold, newAltitudeThreshold);
        esp32CommunicationService.updateEmail(newEmail);

        sensorDataService.updateThresholds(newTemperatureThreshold, newHumidityThreshold, newPressureThreshold,
                newAltitudeThreshold);
        sensorDataService.updateEmail(newEmail);
        return "redirect:/weather-api";
    }
}