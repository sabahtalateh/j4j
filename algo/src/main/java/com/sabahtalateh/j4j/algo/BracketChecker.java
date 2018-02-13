package com.sabahtalateh.j4j.algo;

import java.util.LinkedList;

/**
 * BracketChecker.
 */
public class BracketChecker {

    /**
     * @param line line of brackets.
     * @return is line correct.
     */
    public boolean check(String line) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : line.toCharArray()) {

            if (this.isOpening(c)) {
                stack.addLast(c);
            }

            if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char stackTop = stack.removeLast();
                if (!isComplement(c, stackTop)) {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }

    /**
     * @param c char.
     * @return is opening.
     */
    private boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    /**
     * @param c char.
     * @return is closing.
     */
    private boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    /**
     * @param c1 open or close bracket.
     * @param c2 open or close bracket.
     * @return is complement.
     */
    private boolean isComplement(char c1, char c2) {
        return c1 == '(' && c2 == ')'
                || c1 == ')' && c2 == '('
                || c1 == '[' && c2 == ']'
                || c1 == ']' && c2 == '['
                || c1 == '{' && c2 == '}'
                || c1 == '}' && c2 == '{';
    }
}
