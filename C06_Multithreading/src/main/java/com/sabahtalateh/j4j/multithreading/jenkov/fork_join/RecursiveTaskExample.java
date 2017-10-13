package com.sabahtalateh.j4j.multithreading.jenkov.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTaskExample.
 */
public class RecursiveTaskExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            integers.add(1);
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveTask task = new MyRecursiveTask(integers);

        System.out.println(forkJoinPool.invoke(task));

    }

    /**
     * MyRecursiveTask.
     */
    private static class MyRecursiveTask extends RecursiveTask<Long> {

        private final List<Integer> integers;

        /**
         * @param integers integers.
         */
        MyRecursiveTask(List<Integer> integers) {
            this.integers = integers;
        }

        /**
         * @return sum.
         */
        @Override
        protected Long compute() {
            if (integers.size() > 256) {
                System.out.printf("Splitting [%s] into tasks.%n", integers.size());

                List<MyRecursiveTask> subtasks = createSubtasks();

                subtasks.forEach(ForkJoinTask::fork);

                long result = 0;

                for (MyRecursiveTask task : subtasks) {
                    result += task.join();
                }

                return result;
            } else {
                System.out.printf("Doing [%s] by myself.%n", integers.size());
                return integers.stream().map(Integer::longValue).reduce(0L, (a, b) -> a + b);
            }
        }

        /**
         * @return subtasks.
         */
        private List<MyRecursiveTask> createSubtasks() {
            int middle = integers.size() / 2;
            List<Integer> integersLeft = integers.subList(0, middle);
            List<Integer> integersRight = integers.subList(middle, integers.size());

            return new ArrayList<MyRecursiveTask>() {{
                add(new MyRecursiveTask(integersLeft));
                add(new MyRecursiveTask(integersRight));
            }};
        }
    }
}
