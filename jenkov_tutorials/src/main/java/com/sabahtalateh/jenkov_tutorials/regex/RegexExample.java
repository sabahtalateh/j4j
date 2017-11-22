package com.sabahtalateh.jenkov_tutorials.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexExample.
 */
public class RegexExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String s = "This is a link http://google.com";

        String regex = ".*http://.*";

        boolean matches = Pattern.matches(regex, s);

        System.out.println(matches);

        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        Pattern pattern = Pattern.compile("is");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.printf("found: (%d) %s - %s%n", count, matcher.start(), matcher.end());
        }

    }
}
