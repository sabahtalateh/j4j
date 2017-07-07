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
     * @param answer to io.
     */
    void answer(String answer);
}
