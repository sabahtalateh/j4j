package com.sabahtalateh.jcstress.list;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.L_Result;

/**
 * ArrayListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "Third", expect = Expect.ACCEPTABLE, desc = "First two elements were removed.")
public class ArrayListRemoveTest {

    private List<String> list = new ArrayList<String>() {{
        add("First");
        add("Second");
        add("Third");
    }};

    @Actor
    void actor1() {
        list.remove(0);
    }

    @Actor
    void actor2() {
        list.remove(0);
    }

    @Arbiter
    void arbiter(L_Result result) {
        result.r1 = list.get(0);
    }

}
