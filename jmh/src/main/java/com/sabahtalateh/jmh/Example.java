package com.sabahtalateh.jmh;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example.
 */
public class Example {
    @Benchmark
    public void testAdd() {
        final List<String> strings = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            strings.add("Jopka");
        }
    }
}
