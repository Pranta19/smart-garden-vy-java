package com.smartgarden.controller;

import com.smartgarden.model.Garden;
import com.smartgarden.model.Plant;
import com.smartgarden.model.WaterPump;

import java.util.Map;

/**
 * GardenController orchestrates sensor reads and watering actions.
 */
public class GardenController {
    private final Garden garden;
    private final SensorController sensorController;

    public GardenController(Garden garden, SensorController sensorController) {
        this.garden = garden;
        this.sensorController = sensorController;
    }

    /**
     * Poll sensors and decide watering actions.
     */
    public void evaluateAndAct() {
        Map<Integer, Double> readings = sensorController.poll();
        WaterPump pump = garden.getWaterPump();

        for (Plant plant : garden.getPlants()) {
            double measured = readings.getOrDefault(plant.getId(), plant.getSoilMoisture());
            // update plant moisture with sensor measured value
            plant.setSoilMoisture(measured);

            if (plant.needsWater()) {
                // Simple controller: water until moisture is above threshold
                double deficit = plant.getMoistureThreshold() - plant.getSoilMoisture();
                if (deficit <= 0) continue;
                double seconds = Math.min(5.0, Math.max(0.5, deficit / pump.getFlowRate()));
                pump.water(plant, seconds);
                // NOTE: In a more layered app, we would not System.out.println in the controller,
                // but for this demo, print the action so the user can see what happened.
                System.out.printf("%s - Watering plant '%s' for %.2f sec (deficit %.2f -> moisture now %.1f)\n",
                        java.time.LocalTime.now().withNano(0), plant.getName(), seconds, deficit, plant.getSoilMoisture());
            }
        }
    }
}
