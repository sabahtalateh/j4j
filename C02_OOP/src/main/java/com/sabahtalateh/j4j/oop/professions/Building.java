package com.sabahtalateh.j4j.oop.professions;

/**
 * Building.
 */
public class Building {
    /**
     * Max floors in building.
     */
    private int maxFloors;


    /**
     * Floors in building.
     */
    private int floors;

    /**
     * @param maxFloors Max floors in building.
     */
    public Building(int maxFloors) {
        this.maxFloors = maxFloors;
        this.floors = 0;
    }

    /**
     * @return Max floors.
     */
    public int getMaxFloors() {
        return maxFloors;
    }

    /**
     * @return Floors.
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Build one more floor.
     */
    public void buildFloor() {
        if (floors < maxFloors) {
            floors++;
        }
    }

    /**
     * @return boolean.
     */
    public boolean floorCanBeBuilt() {
        return floors < maxFloors;
    }

    /**
     * @return if building is finished.
     */
    public boolean finished() {
        return floors == maxFloors;
    }
}
