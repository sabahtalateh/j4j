package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * BasicExample.
 */
public class BasicExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        User user = new User(0, "Ivan", 176);

        Class<?> aClass = user.getClass();
        System.out.println(aClass.getName());
        System.out.println(aClass.getSimpleName());

        int modifiers = aClass.getModifiers();
        System.out.println("is final  = " + Modifier.isFinal(modifiers));
        System.out.println("is public = " + Modifier.isPublic(modifiers));

        Package aPackage = aClass.getPackage();
        System.out.println(aPackage.getName());

        Class<?> superclass = aClass.getSuperclass();
        System.out.println(superclass);
        System.out.println(Arrays.toString(aClass.getInterfaces()));
        System.out.println(Arrays.toString(aClass.getConstructors()));
        System.out.println(Arrays.toString(aClass.getFields()));
        System.out.println(Arrays.toString(aClass.getAnnotations()));
    }
}
