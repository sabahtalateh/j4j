package com.sabahtalateh.j4j.multithreading.threads.symbols_counter;

/**
 * SymbolsCounter.
 */
public class SymbolsCounter implements Runnable {

    private final String text;

    private int symbolsCount = 0;

    /**
     * @param text text.
     */
    public SymbolsCounter(String text) {
        this.text = text;
    }

    /**
     * Run process.
     */
    @Override
    public void run() {
        for (char c : this.text.toCharArray()) {
            if (Thread.interrupted()) {
                System.out.println("Not done.");
                return;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Not done.");
                return;
            }
            this.symbolsCount++;
        }
    }

    /**
     * @return symbols execute.
     */
    public int getSymbolsCount() {
        return symbolsCount;
    }
}
