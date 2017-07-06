package com.sabahtalateh.j4j.oop.professions;

/**
 * Doctor.
 */
public class Doctor extends Profession {
    /**
     * HP multiplier.
     */
    protected static final int HEAL_MULTIPLIER = 5;

    /**
     * @param name       of person.
     * @param age        of person
     * @param experience of person.
     */
    public Doctor(String name, int age, int experience) {
        super(name, age, experience);
    }

    /**
     * @param human a person.
     */
    public void heal(Human human) {
        if (human.isAlive() && this.canAct()) {
            stamina -= ACTION_STAMINA;
            int healthToAdd = this.intelligence >= MINIMUM_INTELLIGENCE_TO_ACT
                    ? intelligence * HEAL_MULTIPLIER
                    : intelligence * (-HEAL_MULTIPLIER);

            human.addHealthPoints(healthToAdd);
        }
    }
}
