package com.smartgarden.model;

import java.util.Random;

/**
 * A soil moisture sensor tied to a plant.
 */
public class SoilSensor implements Sensor {
    private final Plant plant;
    private final Random random = new Random();

    // sensor noise magnitude
    private final double noiseRange;

    public SoilSensor(Plant plant, double noiseRange) {
        this.plant = plant;
        this.noiseRange = Math.abs(noiseRange);
    }

    public Plant getPlant() { return plant; }

    @Override
    public double read() {
        // return the plant soil moisture plus random noise
        double base = plant.getSoilMoisture();
        double noise = (random.nextDouble() * 2.0 - 1.0) * noiseRange;
        double value = base + noise;
        if (value < 0.0) value = 0.0;
        if (value > 100.0) value = 100.0;
        return value;
    }
}
