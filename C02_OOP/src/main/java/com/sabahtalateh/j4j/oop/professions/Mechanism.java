package com.sabahtalateh.j4j.oop.professions;

/**
 * Mechanism.
 */
public class Mechanism {
    /**
     * Mechanism durability.
     */
    private static final int MAX_DURABILITY = 100;

    /**
     * Mechanism durability.
     */
    private int durability;

    /**
     * Mechanism explosion probability.
     */
    private double explosionProbability;

    /**
     * Mechanism explosion damage.
     */
    private static final int EXPLOSION_DAMAGE = 200;

    /**
     * @param explosionProbability of mechanism.
     */
    public Mechanism(double explosionProbability) {
        this.durability = MAX_DURABILITY;
        this.explosionProbability = explosionProbability;
    }

    /**
     * @return durability.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * @return explosion probability.
     */
    public double getExplosionProbability() {
        return explosionProbability;
    }

    /**
     * @return if mechanism explodes.
     */
    public boolean explodes() {
        double probability = Math.random();
        return probability <= explosionProbability;
    }

    /**
     * Repair the mechanism.
     */
    public void repair() {
        this.durability = MAX_DURABILITY;
    }

    /**
     * @return Explosion damage.
     */
    public int getExplosionDamage() {
        return EXPLOSION_DAMAGE;
    }
}
