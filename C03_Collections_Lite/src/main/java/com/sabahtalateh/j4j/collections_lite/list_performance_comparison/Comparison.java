package com.sabahtalateh.j4j.collections_lite.list_performance_comparison;

//import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Comparison.
 */
public class Comparison {

    String[] randomStrings;

    /**
     * @param collection o add.
     * @param n          number of elements.
     * @return time to add.
     */
    public long add(Collection<String> collection, int n) {
        if (randomStrings == null) {
            randomStrings = new String[n];
            System.out.println("Start random strings generation..");
            for (int i = 0; i < n; i++) {
                this.randomStrings[i] = UUID.randomUUID().toString();
            }
        }
        System.out.println("Start adding..");
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            collection.add(this.randomStrings[i]);
        }
        System.out.println("End adding..");
        return System.currentTimeMillis() - start;
    }

    /**
     * @param collection to delete.
     * @param n number of elements
     * @return time to delete.
     */
    public long delete(Collection<String> collection, int n) {
        System.out.println("Start deleting..");
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            collection.remove(this.randomStrings[i]);
        }
        System.out.println("End deleting..");
        return System.currentTimeMillis() - start;
    }

    /**
     * @param args of method.
     */
    public static void main(String[] args) {
        Comparison comparison = new Comparison();

        int addSize = (int) 10e8;
        int deleteSize = (int) 10e2;

        Collection<String> arrayList = new ArrayList<>();
        System.out.println("Array list");
        System.out.println("Add: " + comparison.add(arrayList, addSize));
        System.out.println("Delete: " + comparison.delete(arrayList, deleteSize));

        Collection<String> linkedList = new LinkedList<>();
        System.out.println("Linked list");
        System.out.println("Add: " + comparison.add(linkedList, addSize));
        System.out.println("Delete: " + comparison.delete(linkedList, deleteSize));

//        Collection<String> treeSet = new TreeSet<>();
//        System.out.println("Tree set");
//        System.out.println("Add: " + comparison.add(treeSet, addSize));
    }

//    public static void main(String[] args) {
//        int size = (int) 10e6;
//
//        System.out.print(String.format("Max: %.3fGiB%n", Runtime.getRuntime().maxMemory() / (1024.0 * 1024.0 * 1024.0)));
//        System.out.print(String.format("Total: %.3fGiB%n", Runtime.getRuntime().totalMemory() / (1024.0 * 1024.0 * 1024.0)));
//        System.out.print(String.format("Free: %.3fGiB%n", Runtime.getRuntime().freeMemory() / (1024.0 * 1024.0 * 1024.0)));
//        long mem = Runtime.getRuntime().freeMemory();
//
//        List<String> arrayList = new LinkedList<>();
//        for (int i = 0; i < size; i++) {
//            arrayList.add("Long long long long long long long long string " + i);
//        }
//
//        System.out.print(String.format("Max: %.3fGiB%n", Runtime.getRuntime().maxMemory() / (1024.0 * 1024.0 * 1024.0)));
//        System.out.print(String.format("Total: %.3fGiB%n", Runtime.getRuntime().totalMemory() / (1024.0 * 1024.0 * 1024.0)));
//        System.out.print(String.format("Free: %.3fGiB%n", Runtime.getRuntime().freeMemory() / (1024.0 * 1024.0 * 1024.0)));
//
//        long memUsed = mem - Runtime.getRuntime().freeMemory();
//        System.out.print(String.format("Used: %.3fGiB%n", memUsed / (1024.0 * 1024.0 * 1024.0)));
//
//    }
}
