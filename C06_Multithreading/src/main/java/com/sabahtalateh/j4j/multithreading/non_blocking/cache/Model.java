package com.sabahtalateh.j4j.multithreading.non_blocking.cache;

/**
 * Model.
 */
public class Model {

    private String name;

    int version = 0;

    /**
     * @param name name.
     */
    public Model(String name) {
        this.name = name;
    }

    /**
     * @param name    name.
     * @param version version.
     */
    Model(String name, int version) {
        this.name = name;
        this.version = version;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param o object.
     * @return is equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (version != model.version) return false;
        return name != null ? name.equals(model.name) : model.name == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + version;
        return result;
    }
}
