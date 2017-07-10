package com.sabahtalateh.j4j.oop.tracker.io;

import com.sabahtalateh.j4j.oop.tracker.OutOfRangeException;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * ConsoleIO.
 */
public class ConsoleIO implements IO {
    /**
     * @param question to ask.
     * @return answer.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return new Scanner(System.in).nextLine();
    }

    /**
     * @param question      to ask.
     * @param allowedValues to validate.
     * @return answer as integer.
     */
    @Override
    public int ask(String question, int[] allowedValues) {
        System.out.print(question);
        int answer = Integer.valueOf(new Scanner(System.in).nextLine());

        if (IntStream.of(allowedValues).noneMatch(x -> x == answer)) {
            throw new OutOfRangeException("Value is out of allowed range. Please choose another");
        }
        return answer;
    }

    /**
     * @param answer to io.
     */
    @Override
    public void answer(String answer) {
        System.out.println(answer);
    }
}
