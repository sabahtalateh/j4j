package com.sabahtalateh.jenkov_tutorials.json;

import com.google.gson.annotations.Expose;

/**
 * Car.
 */
public class Car {
    @Expose(serialize = true, deserialize = true)
    private String brand = null;

    @Expose(serialize = true, deserialize = true)
    private int doors = 0;

    /**
     * @return brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return doors.
     */
    public int getDoors() {
        return doors;
    }

    /**
     * @param doors doors.
     */
    public void setDoors(int doors) {
        this.doors = doors;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Car{brand='" + brand + "', doors=" + doors + '}';
    }
}
