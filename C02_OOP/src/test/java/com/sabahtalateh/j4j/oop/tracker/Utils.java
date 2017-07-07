package com.sabahtalateh.j4j.oop.tracker;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Test utils.
 */
class Utils {
    /**
     * @param field    to change value.
     * @param newValue of field.
     * @throws Exception e
     */
    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }
}
