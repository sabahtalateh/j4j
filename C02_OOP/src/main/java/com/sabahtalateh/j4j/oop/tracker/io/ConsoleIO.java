package com.sabahtalateh.j4j.oop.tracker.io;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * @param answer to io.
     */
    @Override
    public void answer(String answer) {
        System.out.println(answer);
    }
}
