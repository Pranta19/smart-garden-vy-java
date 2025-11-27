package com.smartgarden.model;

/**
 * Models a simple water pump which can water a plant.
 */
public class WaterPump {
    private final String id;
    private final double flowRate; // moisture percentage per second

    public WaterPump(String id, double flowRate) {
        this.id = id;
        this.flowRate = Math.max(0, flowRate);
    }

    public String getId() { return id; }
    public double getFlowRate() { return flowRate; }

    /**
     * Water the plant for the given duration (seconds).
     */
    public void water(Plant plant, double seconds) {
        if (seconds <= 0) return;
        double amount = flowRate * seconds;
        plant.water(amount);
    }
}
