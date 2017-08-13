package com.sabahtalateh.j4j.multithreading.threads;

/**
 * AnalysisResult.
 */
public class AnalysisResult {
    private long wordsCount;
    private long spacesCount;

    /**
     * @param wordsCount  words count.
     * @param spacesCount spaces count.
     */
    public AnalysisResult(long wordsCount, long spacesCount) {
        this.wordsCount = wordsCount;
        this.spacesCount = spacesCount;
    }

    /**
     * @return string.
     */
    @Override
    public String toString() {
        return "AnalysisResult{"
                + "wordsCount=" + wordsCount
                + ", spacesCount=" + spacesCount + '}';
    }
}
