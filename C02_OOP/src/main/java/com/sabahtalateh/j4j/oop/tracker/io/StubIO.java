package com.sabahtalateh.j4j.oop.tracker.io;

/**
 * StubIO.
 */
public class StubIO implements IO {
    /**
     * @param question to ask.
     * @return answer.
     */
    @Override
    public String ask(String question) {
        return null;
    }

    /**
     * @param answer to io.
     */
    @Override
    public void answer(String answer) {

    }
}
