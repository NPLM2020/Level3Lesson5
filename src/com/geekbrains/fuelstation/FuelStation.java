package com.geekbrains.fuelstation;

import com.geekbrains.vehicles.Vehicle;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FuelStation {
    private static final int parallels = 3;
    private static final int refillingTime = 5000;
    private GasPool gasPool;
    private Semaphore semaphore;
    private LinkedList<Vehicle> queue;
    private ReadWriteLock lock;

    public FuelStation() {
        gasPool = new GasPool();
        semaphore = new Semaphore(parallels);
        queue = new LinkedList<>();
        lock = new ReentrantReadWriteLock();
    }

    public void enter(Vehicle vehicle) {
        if (semaphore.tryAcquire()) {
            boolean isRefill;
            try {
                System.out.printf("\n%s tries to refill.", vehicle.getId());
                isRefill = refill(vehicle);
            } finally {
                semaphore.release();
            }
            new Thread(this::moveQueue).start();
            if (isRefill) {
                 System.out.printf("\n%s has refilled.", vehicle.getId());
                 vehicle.go();
             } else {
                 System.out.printf("\n%s has not refilled and stops working.", vehicle.getId());
             }

        } else {
            lock.writeLock().lock();
            queue.add(vehicle);
            System.out.printf("\n%s has stayed in queue by number %s.", vehicle.getId(), queue.indexOf(vehicle));
            lock.writeLock().unlock();
        }
    }

    private void moveQueue() {
        if (queue.size() > 0) {
            Vehicle vehicle;
            lock.writeLock().lock();
            try {
                vehicle = queue.getFirst();
                queue.removeFirst();
            } finally {
                lock.writeLock().unlock();
            }
            if (vehicle != null) {
                System.out.printf("\n%s go to refill from queue.", vehicle.getId());
                enter(vehicle);
            }
        }
    }

    private boolean refill(Vehicle vehicle) {
        if (gasPool.request(vehicle.getVolume() - vehicle.getLevel())) {
            System.out.printf("\n%s is refilling...", vehicle.getId());
            try {
                Thread.sleep(refillingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vehicle.setLevel(vehicle.getVolume());
            return true;
        }
        return false;
    }

}
