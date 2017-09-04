package com.sabahtalateh.jcstress.list.linked;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.LinkedList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LL_Result;

/**
 * LinkedListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "Hello, World", expect = Expect.ACCEPTABLE, desc = "First thread pasted its value before the second.")
@Outcome(id = "World, Hello", expect = Expect.ACCEPTABLE, desc = "Second thread pasted its value before the first.")
public class LinkedListAddByIndexTest {

    private List<String> list = new LinkedList<>();

    @Actor
    void actor1() {
        list.add(0, "Hello");
    }

    @Actor
    void actor2() {
        list.add(0, "World");
    }

    @Arbiter
    void arbiter(LL_Result result) {
        result.r1 = list.get(0);
        result.r2 = list.get(1);
    }

}
