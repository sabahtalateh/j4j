package com.sabahtalateh.j4j.multithreading.jenkov.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * RecursiveTaskExample.
 */
public class RecursiveTaskExample {
    /**
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(128);

        forkJoinPool.execute(myRecursiveAction);
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
    }

    /**
     * MyRecursiveAction.
     */
    private static class MyRecursiveAction extends RecursiveAction {

        private final long workLoad;

        private final String taskId = UUID.randomUUID().toString().substring(0, 4);

        /**
         * @param workLoad work load.
         */
        MyRecursiveAction(long workLoad) {
            this.workLoad = workLoad;
        }

        /**
         * Compute.
         */
        @Override
        protected void compute() {
            if (workLoad > 8) {
                System.out.printf("[%s] [%s] Splitting workload [%s].%n", Thread.currentThread().getName(), taskId, workLoad);

                List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>() {{
                    addAll(createSubtasks());
                }};

                subtasks.forEach(ForkJoinTask::fork);
            } else {
                System.out.printf("[%s] [%s] Doing work by myself [%s].%n", Thread.currentThread().getName(), taskId, workLoad);
                try {
                    Thread.sleep((int) (Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("[%s] [%s] Done the task.%n", Thread.currentThread().getName(), taskId);
            }
        }

        /**
         * @return subtasks.
         */
        List<MyRecursiveAction> createSubtasks() {
            List<MyRecursiveAction> subtasks = new ArrayList<>();

            MyRecursiveAction subtask1 = new MyRecursiveAction(workLoad / 2);
            MyRecursiveAction subtask2 = new MyRecursiveAction(workLoad / 2);

            subtasks.add(subtask1);
            subtasks.add(subtask2);

            return subtasks;
        }

    }
}
