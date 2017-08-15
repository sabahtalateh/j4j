package com.sabahtalateh.j4j.multithreading.threads.symbols_counter;

/**
 * SymbolsCounterExecutor.
 */
public class SymbolsCounterExecutor {
    /**
     * @param text             text.
     * @param maxExecutionTime max execution time.
     * @return symbols count.
     * @throws InterruptedException exception.
     */
    public int execute(final String text, final long maxExecutionTime) throws InterruptedException {

        TimeWatcher timeWatcher = new TimeWatcher(maxExecutionTime);
        Thread timeWatcherThread = new Thread(timeWatcher);
        timeWatcherThread.start();

        SymbolsCounter symbolsCounter = new SymbolsCounter(text);
        Thread symbolsCounterThread = new Thread(symbolsCounter);
        symbolsCounterThread.start();

        while (symbolsCounterThread.isAlive()) {
            symbolsCounterThread.join(20);

            if (timeWatcher.isTimeOver()) {
                symbolsCounterThread.interrupt();
                timeWatcherThread.interrupt();

                symbolsCounterThread.join();
                timeWatcherThread.join();
            }
        }

        return symbolsCounter.getSymbolsCount();
    }
}
