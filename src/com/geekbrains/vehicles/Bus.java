package com.geekbrains.vehicles;

import com.geekbrains.fuelstation.FuelStation;

public class Bus extends Vehicle {
    private final float consumption = 7.5f;
    private final float volume = 40f;

    public Bus(String id, FuelStation fuelStation) {
        super.setConsumption(consumption);
        super.setVolume(volume);
        super.setId(id);
        super.setLevel(volume);
        super.setFuelStation(fuelStation);
    }
}
