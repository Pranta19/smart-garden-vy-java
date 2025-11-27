package com.smartgarden;

import com.smartgarden.model.Plant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlantTest {
    @Test
    public void testNeedsWaterAndWater() {
        Plant p = new Plant("Test", 20.0, 30.0);
        assertTrue(p.needsWater());
        p.water(20.0);
        assertFalse(p.needsWater());
        assertEquals(40.0, p.getSoilMoisture(), 0.1);
    }
}
