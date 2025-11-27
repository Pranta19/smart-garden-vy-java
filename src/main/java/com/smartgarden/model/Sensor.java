package com.smartgarden.model;

/**
 * Generic sensor interface.
 */
public interface Sensor {
    /**
     * Read a value from the sensor. The semantic is sensor-specific.
     * @return the read value
     */
    double read();
}
