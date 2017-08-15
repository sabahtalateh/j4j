package com.sabahtalateh.j4j.multithreading.threads.symbols_counter;

/**
 * SymbolsCounterRun.
 */
public class SymbolsCounterRun {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("Program should accept 2 args: program \"text to count symbols\" max_execution_time");
            System.exit(1);
        }

        String text;
        long maxExecutionTime = 0L;

        text = args[0];
        try {
            maxExecutionTime = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("max_execution_time is not a number");
            System.exit(1);
        }

        System.out.println(new SymbolsCounterExecutor().execute(text, maxExecutionTime));
        System.exit(0);
    }
}
