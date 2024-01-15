-- -- Insert sample sensor data
INSERT INTO sensor_data (timestamp, temperature, humidity, pressure, altitude, temperature_threshold, humidity_threshold, pressure_threshold, altitude_threshold, email)
VALUES
    ('Sun Dec 11 01:59:18 GMT+02:00 2021', 25.5, 60.2, 1014.3, 48.7, 25, 60, 1015, 49, ''),
    ('Mon Dec 12 01:59:18 GMT+02:00 2021', 26.2, 58.9, 1013.8, 49.1, 25, 60, 1015, 49, ''),
    ('Tue Dec 13 01:59:18 GMT+02:00 2021', 24.8, 61.5, 1015.2, 48.3, 25, 60, 1015, 49, '');