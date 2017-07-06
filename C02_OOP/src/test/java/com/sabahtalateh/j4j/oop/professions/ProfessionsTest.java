package com.sabahtalateh.j4j.oop.professions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests.
 */
public class ProfessionsTest {
    @Test
    public void badEngineerWillDie() {
        Engineer badEngineer = new Engineer("Ivan Palkin", 22, 1);
        Building building = new Building(4);

        badEngineer.build(building);
        badEngineer.build(building);
        badEngineer.build(building);
        badEngineer.build(building);

        assertThat(badEngineer.isAlive(), is(false));
        assertThat(building.finished(), is(false));
    }

    @Test
    public void badEngineerWillNotDieIfHeTheDoctorIsHere() {
        Engineer badEngineer = new Engineer("Ivan Palkin", 22, 1);
        Teacher teacher = new Teacher("Natasha", 35, 10);
        Doctor doctor = new Doctor("Doctor Dre", 54, 30);
        Building building = new Building(4);

        teacher.teach(doctor);

        badEngineer.build(building);
        badEngineer.build(building);

        doctor.heal(badEngineer);

        badEngineer.build(building);
        badEngineer.build(building);

        assertThat(badEngineer.isAlive(), is(true));
        assertThat(building.finished(), is(true));
    }

    @Test
    public void badEngineerWillNotDieIfHeWillStudy() {
        Engineer notBadEngineer = new Engineer("Ivan Palkin", 22, 1);
        Teacher teacher = new Teacher("Natasha", 35, 10);
        Building building = new Building(4);

        teacher.teach(notBadEngineer);
        teacher.teach(notBadEngineer);

        notBadEngineer.build(building);
        notBadEngineer.build(building);
        notBadEngineer.build(building);
        notBadEngineer.build(building);

        assertThat(notBadEngineer.isAlive(), is(true));
        assertThat(building.finished(), is(true));
    }

    @Test
    public void bombWillExplodesAnyway() {
        Engineer superEngineer = new Engineer("Ivan Palkin", 22, 1);
        Teacher teacher = new Teacher("Natasha", 35, 10);

        teacher.teach(superEngineer);
        teacher.teach(superEngineer);
        teacher.teach(superEngineer);
        teacher.teach(superEngineer);
        teacher.teach(superEngineer);

        Mechanism bomb = new Mechanism(1D);
        superEngineer.repair(bomb);

        assertThat(superEngineer.isAlive(), is(false));
    }
}
