package com.sabahtalateh.j4j.collections_lite.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserConverter.
 */
public class UserConverter {
    /**
     * @param users to convert.
     * @return Map.
     */
    public Map<Integer, User> convert(List<User> users) {
        Map<Integer, User> result = new HashMap<>();
        for (User user : users) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
