package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.annotation.Annotation;

/**
 * AnnotationExample.
 */
public class AnnotationExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Class<?> classObj = User.class;

        Annotation[] annotations = classObj.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println(myAnnotation.name());
                System.out.println(myAnnotation.value());
            }
        }

        MyAnnotation annotation = User.class.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.value());
    }
}
