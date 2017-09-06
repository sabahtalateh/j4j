package com.sabahtalateh.jcstress.list.linked;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.LinkedList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

/**
 * LinkedListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "false, false", expect = Expect.ACCEPTABLE, desc = "No one element was not added.")
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Both elements were added.")
@Outcome(id = "true, false", expect = Expect.ACCEPTABLE, desc = "First element was added but not second.")

@Outcome(id = "false, true", expect = Expect.FORBIDDEN, desc = "Second element was added before first. " +
        "It is not acceptable, because of happens-before relation." +
        "If you will remove synchronization from ArrayList this result will appear.")
public class AddAndContainsTest {

    private List<String> list = new LinkedList<>();

    @Actor
    void actor1() {
        list.add("Hello");
        list.add("World");
    }

    @Actor
    void actor2(ZZ_Result result) {
        result.r2 = list.contains("World");
        result.r1 = list.contains("Hello");
    }
}
