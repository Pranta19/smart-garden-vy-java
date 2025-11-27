package com.smartgarden.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Garden holds plants, sensors and resources.
 */
public class Garden {
    private final List<Plant> plants = new ArrayList<>();
    private final List<SoilSensor> soilSensors = new ArrayList<>();
    private final WaterPump waterPump;

    public Garden(WaterPump waterPump) {
        this.waterPump = waterPump;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void addSoilSensor(SoilSensor sensor) {
        soilSensors.add(sensor);
    }

    public List<Plant> getPlants() { return List.copyOf(plants); }
    public List<SoilSensor> getSoilSensors() { return List.copyOf(soilSensors); }
    public WaterPump getWaterPump() { return waterPump; }

    public Optional<Plant> findPlantByName(String name) {
        return plants.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
    }

    /**
     * Simulate simple evaporation over time, to slowly reduce moisture.
     * This is called periodically by the simulation.
     */
    public void evaporate(double seconds) {
        // evaporation rate: 0.02% per second for each plant
        double ratePerSecond = 0.02;
        for (Plant p : plants) {
            p.evaporate(ratePerSecond * seconds);
        }
    }
}
