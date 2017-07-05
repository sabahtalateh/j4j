package com.sabahtalateh.oop.professions;

/**
 * Profession.
 */
public class Profession extends Human {
    /**
     * Where is the human was graduated.
     */
    private String graduation;


    /**
     * Work experience.
     */
    private int experience;

    /**
     * @param name of person.
     * @param age of person
     * @param experience of person.
     */
    public Profession(String name, int age, int experience) {
        super(name, age);
        this.experience = experience;
    }

    /**
     * @return experience.
     */
    public int getExperience() {
        return experience;
    }
}
