package com.sabahtalateh.jcstress.list.array;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LL_Result;

/**
 * ArrayListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "Hello, World", expect = Expect.ACCEPTABLE, desc = "First thread pasted its value before the second.")
@Outcome(id = "World, Hello", expect = Expect.ACCEPTABLE, desc = "Second thread pasted its value before the first.")
public class ArrayListAddByIndexTest {

    private List<String> list = new ArrayList<>();

    @Actor
    void actor1() {
        list.add(1, "Hello");
    }

    @Actor
    void actor2() {
        list.add(1, "World");
    }

    @Arbiter
    void arbiter(LL_Result result) {
        result.r1 = list.get(1);
        result.r2 = list.get(2);
    }

}
