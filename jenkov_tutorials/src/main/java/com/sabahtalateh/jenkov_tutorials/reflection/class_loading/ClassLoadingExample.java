package com.sabahtalateh.jenkov_tutorials.reflection.class_loading;

/**
 * ClassLoadingExample.
 */
public class ClassLoadingExample {
    /**
     * @param args args.
     * @throws ClassNotFoundException exception.
     * @throws IllegalAccessException exception.
     * @throws InstantiationException exception.
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader parentClassLoader = ClassLoadingExample.class.getClassLoader();
        MyClassLoader myClassLoader = new MyClassLoader(parentClassLoader);

        Class<?> myObjectClass = myClassLoader
                .loadClass("com.sabahtalateh.jenkov_tutorials.reflection.class_loading.MyObject");

        MySuperObject myObject0 = (MySuperObject) myObjectClass.newInstance();
        MyInterface myObject1 = (MyInterface) myObjectClass.newInstance();
        myObject0.hello();
        myObject1.hello();

        myClassLoader = new MyClassLoader(parentClassLoader);
        myObjectClass = myClassLoader.loadClass("com.sabahtalateh.jenkov_tutorials.reflection.class_loading.MyObject2");

        MyInterface myObject2 = (MyInterface) myObjectClass.newInstance();
        MySuperObject myObject3 = (MySuperObject) myObjectClass.newInstance();
        myObject2.hello();
        myObject3.hello();
    }
}
