package com.smartgarden.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a plant in the garden.
 */
public class Plant {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

    private final int id;
    private final String name;

    // Soil moisture percentage 0.0 - 100.0
    private double soilMoisture;

    // Threshold under which watering is needed (0-100)
    private final double moistureThreshold;

    public Plant(String name, double initialMoisture, double moistureThreshold) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.name = name;
        this.soilMoisture = Math.max(0.0, Math.min(100.0, initialMoisture));
        this.moistureThreshold = moistureThreshold;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public synchronized double getSoilMoisture() { return soilMoisture; }

    public synchronized void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = Math.max(0.0, Math.min(100.0, soilMoisture));
    }

    public double getMoistureThreshold() { return moistureThreshold; }

    public synchronized boolean needsWater() {
        return soilMoisture < moistureThreshold;
    }

    public synchronized void water(double amount) {
        setSoilMoisture(this.soilMoisture + Math.abs(amount));
    }

    public synchronized void evaporate(double amount) {
        setSoilMoisture(this.soilMoisture - Math.abs(amount));
    }

    @Override
    public String toString() {
        return String.format("Plant[%d] %s: moisture=%.1f%% (threshold=%.1f%%)", id, name, soilMoisture, moistureThreshold);
    }
}
