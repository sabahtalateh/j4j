package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * GenericsExample.
 */
public class GenericsExample {
    /**
     * @param args args.
     * @throws NoSuchMethodException exception.
     */
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = User.class.getMethod("getAttributes");
        System.out.println(method);

        Type returnType = method.getGenericReturnType();
        System.out.println(returnType);

        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArgs = type.getActualTypeArguments();
            for (Type typeArg : typeArgs) {
                Class<?> typeArgClass = (Class) typeArg;
                System.out.println(typeArgClass);
            }
        }
    }
}
