package com.sabahtalateh.j4j.oop.professions;

/**
 * Human.
 */
public class Human {
    /**
     * Humans name.
     */
    protected String name;

    /**
     * Humans age.
     */
    protected int age;

    /**
     * Humans HP.
     */
    protected int healthPoints;

    /**
     * Humans intelligence.
     */
    protected int intelligence;

    /**
     * Humans stamina.
     */
    protected int stamina;

    /**
     * Humans satiety.
     */
    protected int satiety;

    /**
     * Stamina decrease per action.
     */
    protected static final int ACTION_STAMINA = 20;

    /**
     * Minimum intelligence to act.
     */
    protected static final int MINIMUM_INTELLIGENCE_TO_ACT = 20;

    /**
     * Max satiety.
     */
    private static final int MAX_SATIETY = 100;

    /**
     * Max HP.
     */
    private static final int MAX_HEALTH_POINTS = 100;

    /**
     * Max stamina.
     */
    private static final int MAX_STAMINA = 100;

    /**
     * Max stamina.
     */
    private static final int DEFAULT_INTELLIGENCE = 10;

    /**
     * @param name of human
     * @param age  of human
     */
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        satiety = MAX_SATIETY;
        stamina = MAX_STAMINA;
        intelligence = DEFAULT_INTELLIGENCE;
        healthPoints = MAX_HEALTH_POINTS;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @return is human alive.
     */
    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    /**
     * Eat.
     */
    public void eat() {
        if (isAlive()) {
            satiety += 10;
            if (satiety > MAX_SATIETY) {
                satiety = MAX_SATIETY;
            }
        }
    }

    /**
     * Sleep.
     */
    public void sleep() {
        if (isAlive()) {
            stamina += 30;
            if (stamina > MAX_STAMINA) {
                stamina = MAX_STAMINA;
            }
        }
    }

    /**
     * @param healthPoints to add.
     */
    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    /**
     * @return can the human do action.
     */
    public boolean canAct() {
        return this.stamina >= ACTION_STAMINA && isAlive();
    }

    /**
     * @return HP.
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @return intelligence.
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return stamina.
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * @return satiety.
     */
    public int getSatiety() {
        return satiety;
    }
}
