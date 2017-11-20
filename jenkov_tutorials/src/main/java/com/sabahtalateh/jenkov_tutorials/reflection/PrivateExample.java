package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.reflect.Field;

/**
 * PrivateExample.
 */
public class PrivateExample {
    /**
     * @param args args.
     * @throws NoSuchFieldException   exception.
     * @throws IllegalAccessException exception.
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User(0, "Petr", 43);

        System.out.println(user);

        Field nameField = User.class.getDeclaredField("name");
        System.out.println(nameField);

        nameField.setAccessible(true);
        nameField.set(user, "Ivan");
        System.out.println(user);
    }
}
