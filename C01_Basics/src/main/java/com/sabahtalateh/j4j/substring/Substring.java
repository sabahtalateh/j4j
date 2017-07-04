package com.sabahtalateh.j4j.substring;

/**
 * Substring.
 */
public class Substring {
    /**
     * Check if `origin` contains `substring`.
     *
     * @param origin string.
     * @param substring string.
     * @return contains.
     */
    public boolean contains(String origin, String substring) {

        char[] originChars = origin.toCharArray();
        char[] substringChars = substring.toCharArray();

        boolean contains = false;

        for (int i = 0; i < originChars.length - substringChars.length + 1; i++) {
            contains = true;
            for (int j = 0; j < substringChars.length; j++) {
                if (originChars[i + j] != substringChars[j]) {
                    contains = false;
                    break;
                }
            }
            if (contains) {
                break;
            }
        }

        return contains;
    }
}
