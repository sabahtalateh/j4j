package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ConstructorExample.
 */
public class ConstructorExample {
    /**
     * @param args args.
     * @throws Exception exception.
     */
    public static void main(String[] args) throws Exception {
        Class<?> userClass = Class.forName("com.sabahtalateh.jenkov_tutorials.reflection.User");
        Constructor<?> constructor = userClass.getConstructor(int.class, String.class, int.class);

        System.out.println(Arrays.toString(constructor.getParameterTypes()));

        User ivan = (User) constructor.newInstance(0, "Ivan", 176);
        System.out.println(ivan);

        Field stringField = userClass.getField("string");
        System.out.println(stringField.getName());
        System.out.println(stringField.getType());
        System.out.println(stringField);

        stringField.set(ivan, "Hello!");
        System.out.println(ivan);
        System.out.println(stringField.get(ivan));

        Method getName = userClass.getMethod("getName");
        Method setName = userClass.getMethod("setName", String.class);
        System.out.println(getName.getReturnType());
        setName.invoke(ivan, "Petr");
        System.out.println(getName.invoke(ivan));

        userClass.getMethod("staticMethod").invoke(null);

        printGettersAndSetters(userClass);
    }

    /**
     * @param classObj class object.
     */
    public static void printGettersAndSetters(Class<?> classObj) {
        for (Method method : classObj.getMethods()) {
            if (isGetter(method)) {
                System.out.println("Getter: " + method.getName());
            }

            if (isSetter(method)) {
                System.out.println("Setter: " + method.getName());
            }
        }
    }

    /**
     * @param method method.
     * @return is getter.
     */
    public static boolean isGetter(Method method) {
        return method.getName().startsWith("get")
                && 0 == method.getParameterTypes().length
                && !method.getReturnType().equals(void.class);
    }

    /**
     * @param method method.
     * @return is setter.
     */
    public static boolean isSetter(Method method) {
        return method.getName().startsWith("set")
                && 1 == method.getParameterTypes().length;
    }
}
