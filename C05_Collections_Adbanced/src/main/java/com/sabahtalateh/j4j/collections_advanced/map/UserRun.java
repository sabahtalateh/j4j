package com.sabahtalateh.j4j.collections_advanced.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * UserRun.
 */
public class UserRun {
    /**
     * @param args arguments.
     */
    public static void main(String[] args) {
        User ivan0 = new User("Ivan", 1, new GregorianCalendar(1984, 0, 1));
        User ivan1 = new User("Ivan", 1, new GregorianCalendar(1984, 0, 1));

        Map<User, Object> map = new HashMap<User, Object>() {{
            put(ivan0, 1);
            put(ivan1, 2);
        }};

        System.out.println(map);
    }
}
