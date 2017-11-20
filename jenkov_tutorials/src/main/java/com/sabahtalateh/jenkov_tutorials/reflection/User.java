package com.sabahtalateh.jenkov_tutorials.reflection;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * User.
 */
@ThreadSafe
@MyAnnotation(name = "message", value = "Hello World")
public class User {
    private int id;
    private String name;
    private int age;

    public String string = null;

    public List<String> attributes = new ArrayList<>();

    /**
     * @param id   id.
     * @param name name.
     * @param age  age.
     */
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "User{id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", string='" + string + '\''
                + '}';
    }

    /**
     * Static method.
     */
    public static void staticMethod() {
        System.out.println("STATIC!");
    }

    /**
     * @return attributes.
     */
    public List<String> getAttributes() {
        return attributes;
    }
}
