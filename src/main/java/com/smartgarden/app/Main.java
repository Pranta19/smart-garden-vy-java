package com.smartgarden.app;

import com.smartgarden.model.*;
import com.smartgarden.controller.*;
import com.smartgarden.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point for the Smart Garden demo application.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create water pump (flow rate = 2.5% moisture per second)
        WaterPump pump = new WaterPump("pump-1", 2.5);
        com.smartgarden.model.Garden garden = new com.smartgarden.model.Garden(pump);

        // Create plants
        com.smartgarden.model.Plant rosemary = new com.smartgarden.model.Plant("Rosemary", 35.0, 45.0);
        com.smartgarden.model.Plant tomato = new com.smartgarden.model.Plant("Tomato", 60.0, 50.0);
        com.smartgarden.model.Plant basil = new com.smartgarden.model.Plant("Basil", 20.0, 45.0);

        garden.addPlant(rosemary);
        garden.addPlant(tomato);
        garden.addPlant(basil);

        // Create sensors and attach to garden
        com.smartgarden.model.SoilSensor s1 = new com.smartgarden.model.SoilSensor(rosemary, 2.0);
        com.smartgarden.model.SoilSensor s2 = new com.smartgarden.model.SoilSensor(tomato, 2.0);
        com.smartgarden.model.SoilSensor s3 = new com.smartgarden.model.SoilSensor(basil, 2.0);

        garden.addSoilSensor(s1);
        garden.addSoilSensor(s2);
        garden.addSoilSensor(s3);

        List<com.smartgarden.model.SoilSensor> sensors = new ArrayList<>();
        sensors.add(s1); sensors.add(s2); sensors.add(s3);

        com.smartgarden.controller.SensorController sensorController = new com.smartgarden.controller.SensorController(sensors);
        com.smartgarden.controller.GardenController gardenController = new com.smartgarden.controller.GardenController(garden, sensorController);
        ConsoleView view = new ConsoleView(garden);

        System.out.println("Starting Smart Garden simulation (press Ctrl+C to stop)\n");

        // Run a simple loop, simulating time moving forward
        int steps = 20;
        if (args.length > 0) {
            try { steps = Integer.parseInt(args[0]); } catch (NumberFormatException e) { /* ignore */ }
        }

        for (int step = 0; step < steps; step++) {
            System.out.printf("Step %d/%d\n", step+1, steps);
            view.render();
            gardenController.evaluateAndAct();
            // simulate time passing and some evaporation
            garden.evaporate(5.0); // 5 seconds
            Thread.sleep(1000);
        }

        System.out.println("Simulation finished.");
    }
}
