package com.sabahtalateh.j4j.collections_advanced.set;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ArraySetImprovedTest.
 */
public class ArraySetImprovedTest {
    @Test
    public void name() throws Exception {
        Set<String> arraySetImproved = new ArraySetImproved<>();
        arraySetImproved.add("1");
        arraySetImproved.add("2");
        arraySetImproved.add("3");
        arraySetImproved.add("4");
        arraySetImproved.add("5");
        arraySetImproved.add("6");
        arraySetImproved.add("7");
    }
}