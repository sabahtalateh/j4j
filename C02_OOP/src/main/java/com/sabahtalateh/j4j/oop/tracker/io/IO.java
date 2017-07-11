package com.sabahtalateh.j4j.oop.tracker.io;

/**
 * IO.
 */
public interface IO {
    /**
     * @param question to ask.
     * @return answer.
     */
    String ask(String question);

    /**
     * @param question      to ask.
     * @param allowedValues to validate.
     * @return answer.
     */
    int ask(String question, int[] allowedValues);

    /**
     * @param answer to io.
     */
    void answer(String answer);
}
