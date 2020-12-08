package com.geekbrains.fuelstation;

public class GasPool {
    private static final float volume = 200f;
    private float level;

    public GasPool() {
        level = volume;
    }

    protected boolean request(float amount) {
        return !(amount >= level);
    }

    protected float getLevel() {
        return level;
    }

    protected void setLevel(float level) {
        this.level = level;
    }
}
