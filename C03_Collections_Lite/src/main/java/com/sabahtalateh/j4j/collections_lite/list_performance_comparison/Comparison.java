package com.sabahtalateh.j4j.collections_lite.list_performance_comparison;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import static java.lang.String.format;

/**
 * Comparison.
 */
public class Comparison {

    /**
     * @param collection o add.
     * @param n          number of elements.
     * @param br         reader.
     * @return time to add.
     * @throws IOException or not.
     */
    public long add(Collection<String> collection, int n, BufferedReader br) throws IOException {
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            collection.add(strings[i]);
        }
        return System.currentTimeMillis() - start;
    }

    /**
     * @param collection to delete.
     * @param n          number of elements
     * @param br         reader.
     * @return time to delete.
     * @throws IOException or not.
     */
    public long delete(Collection<String> collection, int n, BufferedReader br) throws IOException {
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            collection.remove(strings[i]);
        }
        return System.currentTimeMillis() - start;
    }

    /**
     * @param args of method.
     * @throws IOException or not.
     */
    public static void main(String[] args) throws IOException {
//        TestFileGenerator.generate("out.txt", (int) 1e8);

        InputStream fis = new FileInputStream("out.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);


        if (args[0].equals("linked_list")) {
            System.out.println("Linked list:");
            Collection<String> linkedList = new LinkedList<>();
            int linkedListAddAmount = Integer.valueOf(args[1]);
            long linkedListAddTime = new Comparison().add(linkedList, linkedListAddAmount, br);
            System.out.println(format(
                    "Add    1e%d els.   : %.3f seconds",
                    String.valueOf(linkedListAddAmount).length() - 1,
                    linkedListAddTime / 1000.0)
            );

            int linkedListDeleteAmount = Integer.valueOf(args[2]);
            long linkedListDeleteTime = new Comparison().delete(linkedList, linkedListDeleteAmount, br);
            System.out.println(format(
                    "Delete 1e%d els.   : %.3f seconds",
                    String.valueOf(linkedListDeleteAmount).length() - 1,
                    linkedListDeleteTime / 1000.0)
            );
        }

        if (args[0].equals("array_list")) {
            System.out.println("Array list:");
            Collection<String> arrayList = new ArrayList<>();

            int arrayListAddAmount = Integer.valueOf(args[1]);
            long arrayListAddTime = new Comparison().add(arrayList, arrayListAddAmount, br);
            System.out.println(format(
                    "Add    1e%d els.   : %.3f seconds",
                    String.valueOf(arrayListAddAmount).length() - 1,
                    arrayListAddTime / 1000.0)
            );


            int arrayListDeleteAmount = Integer.valueOf(args[2]);
            long arrayListDeleteTime = new Comparison().delete(arrayList, arrayListDeleteAmount, br);
            System.out.println(format(
                    "Delete 1e%d els.   : %.3f seconds",
                    String.valueOf(arrayListDeleteAmount).length() - 1,
                    arrayListDeleteTime / 1000.0)
            );
        }

        if (args[0].equals("tree_set")) {
            System.out.println("Tree set:");
            Collection<String> treeSet = new TreeSet<>();

            int treeSetAddAmount = Integer.valueOf(args[1]);
            long treeSetAddTime = new Comparison().add(treeSet, treeSetAddAmount, br);
            System.out.println(format(
                    "Add    1e%d els.   : %.3f seconds",
                    String.valueOf(treeSetAddAmount).length() - 1,
                    treeSetAddTime / 1000.0)
            );


            int treeSetDeleteAmount = Integer.valueOf(args[2]);
            long arrayListDeleteTime = new Comparison().delete(treeSet, treeSetDeleteAmount, br);
            System.out.println(format(
                    "Delete 1e%d els.   : %.3f seconds",
                    String.valueOf(treeSetDeleteAmount).length() - 1,
                    arrayListDeleteTime / 1000.0)
            );
        }
    }
}
