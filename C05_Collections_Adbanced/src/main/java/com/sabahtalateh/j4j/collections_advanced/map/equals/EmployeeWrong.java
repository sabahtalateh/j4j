package com.sabahtalateh.j4j.collections_advanced.map.equals;

/**
 * Employee.
 */
public class EmployeeWrong extends UserWrong {

    private String position;

    /**
     * @param name     name.
     * @param age      age.
     * @param position position.
     */
    public EmployeeWrong(String name, int age, String position) {
        super(name, age);
        this.position = position;
    }

    /**
     * @param o object.
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return false;
        }

        if (o == null) {
            return false;
        }

        // Call equals on argument, for example on UserWrong class.
        if (!(o instanceof EmployeeWrong)) {
            return o.equals(this);
        }

        EmployeeWrong emp = (EmployeeWrong) o;

        boolean result;
        if (this.position == null) {
            result = emp.position == null;
        } else {
            result = this.position.equals(emp.position);
        }

        return super.equals(o) && result;
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
