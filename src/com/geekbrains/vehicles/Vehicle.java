package com.geekbrains.vehicles;

import com.geekbrains.fuelstation.FuelStation;

public abstract class Vehicle implements Runnable {
    private static final int consumptionPeriod = 3000;
    private String id;
    private float volume;
    private float consumption;
    private float level;
    private FuelStation fuelStation;

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public float getVolume() {
        return volume;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setVolume(float volume) {
        this.volume = volume;
    }

    protected void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public void go() {
        try {
            System.out.printf("\n%s has started moving.", id);
            while (level >= consumption) {
                System.out.printf("\n%s is moving. Remaining fuel is %s.", id, level);
                Thread.sleep(consumptionPeriod);
                level -= consumption;
            }
            System.out.printf("\n%s needs to refill.", id);
            fuelStation.enter(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        go();
    }
}
