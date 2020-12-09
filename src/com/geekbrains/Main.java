package com.geekbrains;

import com.geekbrains.fuelstation.FuelStation;
import com.geekbrains.vehicles.Bus;
import com.geekbrains.vehicles.Car;
import com.geekbrains.vehicles.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        FuelStation fuelStation = new FuelStation();
        Car car1 = new Car("Car1", fuelStation);
        Car car2 = new Car("Car2", fuelStation);
        Car car3 = new Car("Car3", fuelStation);
        Car car4 = new Car("Car4", fuelStation);
        Car car5 = new Car("Car5", fuelStation);
        Car car6 = new Car("Car6", fuelStation);
        Bus bus1 = new Bus("Bus1", fuelStation);
        Truck truck1 = new Truck("Truck1", fuelStation);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(car1);
        executorService.submit(car2);
        executorService.submit(car3);
        executorService.submit(car4);
        executorService.submit(car5);
        executorService.submit(car6);
        executorService.submit(bus1);
        executorService.submit(truck1);

        executorService.shutdown();

    }
}
