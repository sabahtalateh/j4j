package com.sabahtalateh.j4j.oop.tracker.io;

import java.util.Arrays;

/**
 * StubIO.
 */
public class StubIO implements IO {

    public String[] actionSequence;

    private int actionPointer = 0;

    private String[] answers = new String[1000];

    private int answerPointer = 0;

    /**
     * @param question to ask.
     * @return answer.
     */
    @Override
    public String ask(String question) {
        return this.actionSequence[actionPointer++];
    }

    /**
     * @param answer to io.
     */
    @Override
    public void answer(String answer) {
        // Filter menu printing.
        if (!answer.startsWith("[")) {
            this.answers[answerPointer++] = answer;
        }
    }

    /**
     * @param actionSequence to set.
     */
    public void setActionSequence(String[] actionSequence) {
        this.actionSequence = actionSequence;
        actionPointer = 0;
    }

    /**
     * @return get collected answers.
     */
    public String[] getAnswers() {
        return Arrays.copyOfRange(this.answers, 0, answerPointer);
    }
}
