package com.sabahtalateh.jenkov_tutorials.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyAnnotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    /**
     * @return name.
     */
    String name();

    /**
     * @return value.
     */
    String value();
}
