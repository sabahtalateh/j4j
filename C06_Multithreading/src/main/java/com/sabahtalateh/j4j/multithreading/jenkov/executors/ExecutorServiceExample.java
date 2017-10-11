package com.sabahtalateh.j4j.multithreading.jenkov.executors;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorServiceExample.
 */
public class ExecutorServiceExample {
    /**
     * @param args args.
     * @throws InterruptedException exception
     * @throws ExecutionException exception
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> {
            System.out.printf("Async task.%n");
        });

        Future future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Async submit.%n");
        });

        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(1500);
            System.out.println("Async callable.");
            return "Callable result.";
        });

        System.out.println(future1.get());

        String r1 = executorService.invokeAny(new HashSet<Callable<String>>() {{
            add(() -> {
                Thread.sleep(200);
                return "Callable 1";
            });
            add(() -> "Callable 2");
            add(() -> "Callable 3");
        }});

        System.out.println(r1);

        List<Future<String>> futures = executorService.invokeAll(new HashSet<Callable<String>>() {{
            add(() -> {
                Thread.sleep(200);
                return "Callable 1";
            });
            add(() -> {
                Thread.sleep(300);
                return "Callable 2";
            });
            add(() -> {
                Thread.sleep(150);
                return "Callable 3";
            });
        }});

        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        executorService.shutdown();
    }
}
