package com.sabahtalateh.j4j.multithreading.threads;

/**
 * TextAnalysisRun.
 */
public class TextAnalysisRun {

    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        TextAnalysis textAnalysis = new TextAnalysis();
        AnalysisResult report = textAnalysis.analyze(args[0]);
        System.out.println(report);
    }
}
