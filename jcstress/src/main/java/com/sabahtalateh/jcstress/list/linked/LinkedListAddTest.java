package com.sabahtalateh.jcstress.list.linked;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.LinkedList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * LinkedListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "2", expect = Expect.ACCEPTABLE, desc = "All the elements were added to array.")
public class LinkedListAddTest {

    private List<String> list = new LinkedList<>();

    @Actor
    void actor1() {
        list.add("Hello");
    }

    @Actor
    void actor2() {
        list.add("World");
    }

    @Arbiter
    void arbiter(I_Result result) {
        result.r1 = list.size();
    }

}
