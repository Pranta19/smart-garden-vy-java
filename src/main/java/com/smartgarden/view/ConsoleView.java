package com.smartgarden.view;

import com.smartgarden.model.Garden;
import com.smartgarden.model.Plant;

import java.time.LocalTime;
import java.util.List;

/**
 * Very simple console view that prints plant states.
 */
public class ConsoleView {
    private final Garden garden;

    public ConsoleView(Garden garden) {
        this.garden = garden;
    }

    public void render() {
        System.out.println("\n--- Smart Garden Status (" + LocalTime.now().withNano(0) + ") ---");
        List<Plant> plants = garden.getPlants();
        for (Plant p : plants) {
            System.out.println(p.toString() + (p.needsWater() ? " [NEEDS WATER]" : ""));
        }
        System.out.println("--- End of status ---\n");
    }
}
