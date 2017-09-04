package com.sabahtalateh.jcstress.list.array;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LL_Result;

/**
 * LinkedListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "Hello, World", expect = Expect.ACCEPTABLE)
@Outcome(id = "World, Hello", expect = Expect.ACCEPTABLE)
public class ArrayListGetTest {

    private List<String> list = new ArrayList<>();

    @Actor
    void actor1() {
        list.add("Hello");
    }

    @Actor
    void actor2() {
        list.add("World");
    }

    @Arbiter
    void arbiter(LL_Result result) {
        result.r1 = list.get(0);
        result.r2 = list.get(1);
    }

}
