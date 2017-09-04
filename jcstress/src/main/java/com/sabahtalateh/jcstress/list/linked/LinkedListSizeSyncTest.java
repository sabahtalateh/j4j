package com.sabahtalateh.jcstress.list.linked;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.LinkedList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZI_Result;

/**
 * LinkedListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "false, 0", expect = Expect.ACCEPTABLE)
@Outcome(id = "true, 1", expect = Expect.ACCEPTABLE)
public class LinkedListSizeSyncTest {

    private final List<String> list = new LinkedList<>();

    @Actor
    void actor1() {
        list.add("Hello");
    }

    @Actor
    void actor2(ZI_Result result) {
        synchronized (list) {
            result.r1 = list.contains("Hello");
            result.r2 = list.size();
        }
    }
}
