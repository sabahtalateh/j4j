package com.sabahtalateh.oop.professions;

/**
 * Teacher.
 */
public class Teacher extends Profession {
    /**
     * HP multiplier.
     */
    private static final int INTELLIGENCE_MULTIPLIER = 3;

    /**
     * @param name       of person.
     * @param age        of person
     * @param experience of person.
     */
    public Teacher(String name, int age, int experience) {
        super(name, age, experience);
    }

    /**
     * @param human to teach.
     */
    public void teach(Human human) {
        if (human.isAlive() && this.canAct()) {
            stamina -= ACTION_STAMINA;
            int intelligenceToAdd = INTELLIGENCE_MULTIPLIER * intelligence;
            human.intelligence += intelligenceToAdd;
        }

        stamina -= ACTION_STAMINA;
    }
}
