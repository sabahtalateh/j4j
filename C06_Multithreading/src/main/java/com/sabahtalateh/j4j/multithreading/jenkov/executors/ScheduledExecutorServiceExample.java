package com.sabahtalateh.j4j.multithreading.jenkov.executors;

import java.util.concurrent.*;

/**
 * ScheduledExecutorServiceExample.
 */
public class ScheduledExecutorServiceExample {
    /**
     * @param args args.
     * @throws InterruptedException exception
     * @throws ExecutionException exception
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

        ScheduledFuture<String> scheduledFuture = executorService
                .schedule(() -> "Scheduled future 1.", 1, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.get());

        // To see the result do not shutdown the executor service.
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Scheduled executed.");
            }
        }, 2, 2, TimeUnit.SECONDS);


//        executorService.shutdown();
    }
}
