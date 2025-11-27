package com.smartgarden.controller;

import com.smartgarden.model.SoilSensor;
import com.smartgarden.model.Plant;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * SensorController polls sensors and exposes the last-read values.
 */
public class SensorController {
    private final List<SoilSensor> sensors;
    private final Map<Integer, Double> lastReadings = new HashMap<>();

    public SensorController(List<SoilSensor> sensors) {
        this.sensors = sensors;
    }

    public Map<Integer, Double> poll() {
        lastReadings.clear();
        for (SoilSensor s : sensors) {
            double val = s.read();
            Plant p = s.getPlant();
            lastReadings.put(p.getId(), val);
        }
        return Map.copyOf(lastReadings);
    }

    public Double getLastReading(Plant plant) {
        return lastReadings.get(plant.getId());
    }
}
