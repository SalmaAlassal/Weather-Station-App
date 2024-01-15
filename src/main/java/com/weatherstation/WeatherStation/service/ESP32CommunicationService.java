package com.weatherstation.WeatherStation.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

@Service
public class ESP32CommunicationService {
    private final String esp32ThresholdEndpoint = "http://192.168.1.158:80/updateThreshold";
    private final String esp32EmailEndpoint = "http://192.168.1.158:80/updateEmail";

    public void updateThresholds(float temperatureThreshold, float humidityThreshold,
            float pressureThreshold, float altitudeThreshold) {
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                
        String thresholdsJson = "{\"temperatureThreshold\":" + temperatureThreshold + ",\"humidityThreshold\":" + humidityThreshold + ",\"pressureThreshold\":" + pressureThreshold + ",\"altitudeThreshold\":" + altitudeThreshold + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(thresholdsJson, headers);
        
        System.out.println("Sending json thresholds to ESP32: " + thresholdsJson);
        
        // Send the request
        sendRequest(requestEntity, esp32ThresholdEndpoint, "Thresholds");
    }
    
    public void updateEmail(String newEmail) {
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String emailJson = "{\"email\":\"" + newEmail + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(emailJson, headers);

        System.out.println("Sending json email to ESP32: " + emailJson);

        // Send the request
        sendRequest(requestEntity, esp32EmailEndpoint, "Email");
    }

    private void sendRequest(HttpEntity<?> requestEntity, String esp32Endpoint, String name) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(esp32Endpoint, HttpMethod.POST, requestEntity,
                    String.class);
            
            System.out.println("Response from ESP32: " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println(name + " sent to ESP32 successfully");
            } else {
                System.out.println("Failed to send " + name + " to ESP32. Server responded with: " + response.getStatusCode());
            }
        } catch (ResourceAccessException e) {
            System.out.println("Request to ESP32 timed out. Please check your network connection or server status.");
        }
    }
}