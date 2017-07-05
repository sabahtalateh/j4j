package com.sabahtalateh.oop.professions;

/**
 * Engineer.
 */
public class Engineer extends Profession {
    /**
     * Damage thar will be subtracted from HP if intelligence is too little.
     */
    private static final int BUILDING_DAMAGE = 40;

    /**
     * @param name       of person.
     * @param age        of person
     * @param experience of person.
     */
    public Engineer(String name, int age, int experience) {
        super(name, age, experience);
    }

    /**
     * @param building build.
     */
    public void build(Building building) {
        if (this.canAct()) {
            stamina -= ACTION_STAMINA;
            building.buildFloor();
            if (intelligence <= MINIMUM_INTELLIGENCE_TO_ACT) {
                this.healthPoints -= BUILDING_DAMAGE;
            }
        }

    }

    /**
     * @param mechanism to repair.
     */
    public void repair(Mechanism mechanism) {
        if (this.canAct()) {
            if (mechanism.explodes()) {
                this.healthPoints -= mechanism.getExplosionDamage();
            } else {
                mechanism.repair();
            }
        }
    }
}
