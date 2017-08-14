package com.sabahtalateh.j4j.multithreading.threads;

import java.io.PrintStream;

/**
 * TextAnalysis.
 */
public class TextAnalysis {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    private static final long SURROGATE_COUNTING_DELAY = 100;

    private static final long WAITING_TIME = 10000;

    /**
     * @param text text.
     * @return AnalysisResult.
     * @throws InterruptedException exception.
     */
    public AnalysisResult analyze(String text) throws InterruptedException {
        return this.analyze(text, null);
    }

    /**
     * @param text         text.
     * @param reportStream report stream.
     * @return analysis result.
     * @throws InterruptedException exception.
     */
    public AnalysisResult analyze(String text, PrintStream reportStream) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        if (reportStream != null) {
            reportStream.printf("[%s] Start..%n", Thread.currentThread().getName());
        }

        SpacesCounter spacesCounter = reportStream == null
                ? new SpacesCounter(text)
                : new SpacesCounter(text, reportStream);

        WordsCounter wordsCounter = reportStream == null
                ? new WordsCounter(text)
                : new WordsCounter(text, reportStream);

        Thread spacesCounterThread = new Thread(spacesCounter);
        Thread wordsCounterThread = new Thread(wordsCounter);

        spacesCounterThread.start();
        wordsCounterThread.start();

        boolean aborted = false;
        while (spacesCounterThread.isAlive() || wordsCounterThread.isAlive()) {
            if (reportStream != null) {
                reportStream.printf(ANSI_YELLOW + "[%s] Waiting for finish.%n" + ANSI_RESET, Thread.currentThread().getName());
            }
            spacesCounterThread.join(1000);
            wordsCounterThread.join(1000);

            if (System.currentTimeMillis() - startTime > WAITING_TIME) {
                aborted = true;
                if (reportStream != null) {
                    reportStream.printf(ANSI_RED + "!! [%s] Too long. Aborting.%n" + ANSI_RESET, Thread.currentThread().getName());
                }

                spacesCounterThread.interrupt();
                wordsCounterThread.interrupt();

                spacesCounterThread.join();
                wordsCounterThread.join();
            }
        }

        if (aborted) {
            if (reportStream != null) {
                reportStream.printf(ANSI_RED + "!! [%s] Children threads has been aborted.%n" + ANSI_RESET, Thread.currentThread().getName());
            }
        } else {
            if (reportStream != null) {
                reportStream.printf("[%s] Finish.%n", Thread.currentThread().getName());
            }
        }

        return new AnalysisResult(wordsCounter.getWordsCount(), spacesCounter.getSpacesCount());
    }

    /**
     * WordsCounter.
     */
    private class WordsCounter implements Runnable {

        String text;

        long wordsCount = 0;

        PrintStream reportStream = null;

        /**
         * @param text text.
         */
        WordsCounter(String text) {
            this.text = text;
        }

        /**
         * @param text         text.
         * @param reportStream stream to report.
         */
        WordsCounter(String text, PrintStream reportStream) {
            this.text = text;
            this.reportStream = reportStream;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            if (this.text.charAt(0) != ' ') {
                this.wordsCount++;
                if (this.reportStream != null) {
                    this.reportStream.printf("[%s] Words: %d%n", Thread.currentThread().getName(), this.wordsCount);
                }
            }

            for (int i = 1; i < this.text.length(); i++) {
                if (this.text.charAt(i) == ' ' && this.text.charAt(i - 1) != ' ') {
                    if (Thread.interrupted()) {
                        this.reportAborted();
                        return;
                    }

                    try {
                        Thread.sleep(SURROGATE_COUNTING_DELAY);
                    } catch (InterruptedException e) {
                        this.reportAborted();
                        return;
                    }

                    this.wordsCount++;
                    if (this.reportStream != null) {
                        this.reportStream.printf("[%s] Words: %d%n", Thread.currentThread().getName(), this.wordsCount);
                    }
                }
            }

        }

        /**
         * @return words count.
         */
        public long getWordsCount() {
            return wordsCount;
        }

        /**
         * Report abort.
         */
        private void reportAborted() {
            if (this.reportStream != null) {
                this.reportStream.printf(ANSI_RED + "!! [%s] [WordsCounter] Aborted.%n" + ANSI_RESET, Thread.currentThread().getName());
            }
        }
    }

    /**
     * SpacesCounter.
     */
    private class SpacesCounter implements Runnable {

        String text;

        long spacesCount = 0;

        PrintStream reportStream = null;

        /**
         * @param text text.
         */
        SpacesCounter(String text) {
            this.text = text;
        }

        /**
         * @param text         text.
         * @param reportStream stream to report.
         */
        SpacesCounter(String text, PrintStream reportStream) {
            this.text = text;
            this.reportStream = reportStream;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            for (int i = 0; i < this.text.length(); i++) {
                if (Thread.interrupted()) {
                    this.reportAborted();
                    return;
                }

                try {
                    Thread.sleep(SURROGATE_COUNTING_DELAY);
                } catch (InterruptedException e) {
                    this.reportAborted();
                    return;
                }

                if (this.text.charAt(i) == ' ') {
                    this.spacesCount++;
                    if (this.reportStream != null) {
                        this.reportStream.printf("[%s] Spaces: %d%n", Thread.currentThread().getName(), this.spacesCount);
                    }
                }
            }
        }

        /**
         * @return spaces count.
         */
        public long getSpacesCount() {
            return spacesCount;
        }

        /**
         * Report abort.
         */
        private void reportAborted() {
            if (this.reportStream != null) {
                this.reportStream.printf(ANSI_RED + "!! [%s] [SpacesCounter] Aborted.%n" + ANSI_RESET, Thread.currentThread().getName());
            }
        }
    }
}
