package com.sabahtalateh.j4j.multithreading.text_finder;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * TextFinder.
 */
public class TextFinder {

    // One thread will walk through the directories tree, other Threads will search text in files.
    private static final int SEARCHERS_COUNT = Runtime.getRuntime().availableProcessors() > 1
            ? Runtime.getRuntime().availableProcessors() - 1
            : 1;

    /**
     * One thread walks through the filesystem to find filenames that math passed extensions.
     * When such file found it puts it into the queue.
     * Searchers poll the queue and when there is a new file in it,
     *  they take it and search for text to search in the file.
     *
     * @param rootPath     root.
     * @param textToSearch text to search.
     * @param extensions   extensions.
     * @return result.
     * @throws InterruptedException exception.
     */
    List<String> parallelSearch(String rootPath, String textToSearch, List<String> extensions) throws InterruptedException {
        // Here the results will be put.
        List<String> results = new ArrayList<>();

        // Queue for intercommunication between "file system walker" and "searchers"
        BlockingQueue<File> queue = new LinkedBlockingDeque<>();

        // Thread that walks the file system and put files with provided extension in queue.
        Thread fileFinder = new Thread(new FileFinder(queue, new File(rootPath), extensions));

        List<Thread> searchers = new ArrayList<>();
        for (int i = 0; i < SEARCHERS_COUNT; i++) {
            // Searcher takes file from queue and looks for text entry in it.
            searchers.add(new Thread(new TextInFileSearcher(queue, textToSearch, results)));
        }

        // Run all the guys.
        fileFinder.start();
        searchers.forEach(Thread::start);
        searchers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fileFinder.join();

        return results;
    }


    /**
     * FileFinder.
     */
    private static class FileFinder implements Runnable {
        private final BlockingQueue<File> queue;
        private final List<String> extensions;
        private File searchFrom;

        /**
         * @param queue      queue.
         * @param searchFrom search from.
         * @param extensions extensions.
         */
        FileFinder(BlockingQueue<File> queue, File searchFrom, List<String> extensions) {
            this.queue = queue;
            this.searchFrom = searchFrom;
            this.extensions = extensions;
        }

        /**
         * Recursively walks through the filesystem and looks for
         *  files that matches the extensions.
         *
         * @param searchFrom search from.
         */
        void parallelSearch(File searchFrom) {
            for (File file : searchFrom.listFiles()) {
                if (file.isDirectory()) {
                    this.parallelSearch(file);
                }

                if (extensions.stream().anyMatch(x -> file.getName().endsWith(x))) {
                    try {
                        queue.put(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * Run.
         */
        @Override
        public void run() {
            this.parallelSearch(searchFrom);
        }
    }

    /**
     * TextInFileSearcher.
     */
    private static class TextInFileSearcher implements Runnable {

        final BlockingQueue<File> queue;
        final String searchString;
        final List<String> results;

        /**
         * TextInFileSearcher.
         *
         * @param queue        queue.
         * @param searchString tex to search.
         * @param results      result.
         */
        TextInFileSearcher(BlockingQueue<File> queue, String searchString, List<String> results) {
            this.queue = queue;
            this.searchString = searchString;
            this.results = results;
        }

        /**
         * Takes files from queue and looks for the text entry in it.
         *
         * Search text entries in provided files.
         */
        @Override
        public void run() {
            try {
                File file;
                while ((file = queue.poll(100, TimeUnit.MILLISECONDS)) != null) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(searchString)) {
                            // Synchronization requires because of many threads are writing into results.
                            synchronized (results) {
                                results.add(file.getAbsolutePath());
                                break;
                            }
                        }
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
