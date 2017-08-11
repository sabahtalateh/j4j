package com.sabahtalateh.j4j.collections_advanced.map.equals;

/**
 * Employee.
 */
public class Employee extends User {

    private String position;

    /**
     * @param name     name.
     * @param age      age.
     * @param position position.
     */
    public Employee(String name, int age, String position) {
        super(name, age);
        this.position = position;
    }

    /**
     * @param o object.
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return position != null ? position.equals(employee.position) : employee.position == null;
    }

    /**
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
