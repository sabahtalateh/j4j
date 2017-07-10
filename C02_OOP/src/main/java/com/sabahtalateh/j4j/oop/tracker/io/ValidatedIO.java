package com.sabahtalateh.j4j.oop.tracker.io;

import com.sabahtalateh.j4j.oop.tracker.OutOfRangeException;

/**
 * ValidatedIO.
 */
public class ValidatedIO extends ConsoleIO {
    /**
     * @param question      to ask.
     * @param allowedValues to validate.
     * @return validated answer.
     */
    @Override
    public int ask(String question, int[] allowedValues) {
        int answer = -1;
        boolean valid = false;
        do {
            try {
                answer = super.ask(question, allowedValues);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("!! Please type number !!");
            } catch (OutOfRangeException e) {
                System.out.println("!! Typed value is out of allowed range !!");
            }
        } while (!valid);

        return answer;
    }
}
