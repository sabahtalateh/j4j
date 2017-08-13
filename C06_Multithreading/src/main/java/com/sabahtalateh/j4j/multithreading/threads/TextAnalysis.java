package com.sabahtalateh.j4j.multithreading.threads;

/**
 * TextAnalysis.
 */
public class TextAnalysis {
    /**
     * @param text text.
     * @return AnalysisResult.
     * @throws InterruptedException exception.
     */
    public AnalysisResult analyze(String text) throws InterruptedException {
        SpacesCounter spacesCounter = new SpacesCounter(text);
        Thread spacesCounterThread = new Thread(spacesCounter);
        WordsCounter wordsCounter = new WordsCounter(text);
        Thread wordsCounterThread = new Thread(wordsCounter);

        spacesCounterThread.start();
        wordsCounterThread.start();

        spacesCounterThread.join();
        wordsCounterThread.join();

        return new AnalysisResult(wordsCounter.getWordsCount(), spacesCounter.getSpacesCount());
    }

    /**
     * WordsCounter.
     */
    private class WordsCounter implements Runnable {

        String text;

        long wordsCount = 0;

        /**
         * @param text text.
         */
        WordsCounter(String text) {
            this.text = text;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            if (this.text.charAt(0) != ' ') {
                this.wordsCount++;
            }

            for (int i = 1; i < this.text.length(); i++) {
                if (this.text.charAt(i) == ' ' && this.text.charAt(i - 1) != ' ') {
                    this.wordsCount++;
                }
            }

        }

        /**
         * @return words count.
         */
        public long getWordsCount() {
            return wordsCount;
        }
    }

    /**
     * SpacesCounter.
     */
    private class SpacesCounter implements Runnable {

        String text;

        long spacesCount = 0;

        /**
         * @param text text.
         */
        SpacesCounter(String text) {
            this.text = text;
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            for (int i = 0; i < this.text.length(); i++) {
                if (this.text.charAt(i) == ' ') {
                    this.spacesCount++;
                }
            }
        }

        /**
         * @return spaces count.
         */
        public long getSpacesCount() {
            return spacesCount;
        }
    }
}
