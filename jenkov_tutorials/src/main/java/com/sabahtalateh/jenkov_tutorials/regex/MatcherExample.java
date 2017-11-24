package com.sabahtalateh.jenkov_tutorials.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MatcherExample.
 */
public class MatcherExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String text = "This is the text to be searched "
                + "for occurrences of the http:// pattern.";

        String patternString = ".*http://.*";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        // .matches() - check for occurrences.
        System.out.println(matcher.matches());

        patternString = "this is the";
        pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(text);

        // .lookingAt() check just beginning of text.
        System.out.println(matcher.matches());
        System.out.println(matcher.lookingAt());

        matcher = Pattern.compile("is").matcher(text);

        int count = 0;
        while (matcher.find()) {
            System.out.printf("start [%s], end [%s]%n", matcher.start(), matcher.end());
            count++;
        }
        System.out.println("Occurrences " + count);

        //.reset()
        matcher.reset();
        count = 0;
        while (matcher.find()) {
            System.out.printf("start [%s], end [%s]%n", matcher.start(), matcher.end());
            count++;
        }
        System.out.println("Occurrences " + count);

        // .appendReplacement()
        text = "We have such urls http://google.com, https://google.com, http://ya.ru, and of course http://kolbasa.ru.. yep";
        matcher = Pattern.compile("((http|https)://.*?\\.(com|ru))").matcher(text);

        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            matcher.appendReplacement(stringBuffer, "[link]");
            System.out.println(stringBuffer.toString());
        }

        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString());

        // .replaceAll(), .replaceFirst()
        matcher = Pattern.compile("((http|https)://.*?\\.(com|ru))").matcher(text);
        System.out.println(matcher.replaceAll("[link]"));


        // ? is for greedy.
        text = "John went for a walk, and John fell down, and John hurt his knee.";
        matcher = Pattern.compile("J.*?n").matcher(text);

        while (matcher.find()) {
            for (int i = matcher.start(); i < matcher.end(); i++) {
                System.out.print(text.charAt(i));
            }
            System.out.println();
        }

        // non greedy
        text = "John went for a walk, and John fell down, and John hurt his knee.";
        matcher = Pattern.compile("J.*n").matcher(text);

        while (matcher.find()) {
            for (int i = matcher.start(); i < matcher.end(); i++) {
                System.out.print(text.charAt(i));
            }
            System.out.println();
        }

        // possesive
        text = "John went for a walk, and John fell down, and John hurt his knee.";
        matcher = Pattern.compile("John.*+hurt").matcher(text);

        while (matcher.find()) {
            for (int i = matcher.start(); i < matcher.end(); i++) {
                System.out.print(text.charAt(i));
            }
            System.out.println();
        }
    }
}
