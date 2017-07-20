package com.sabahtalateh.j4j.oop.bank;

/**
 * Client.
 */
public class Client {

    private String name;

    /**
     * @param name of client.
     */
    public Client(String name) {
        this.name = name;
    }


    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param o to check.
     * @return result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        return name != null ? name.equals(client.name) : client.name == null;
    }

    /**
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "Client{name='" + name + '}';
    }
}
