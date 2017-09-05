package com.sabahtalateh.jcstress.list;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;
import org.openjdk.jcstress.infra.results.J_Result;
import org.openjdk.jcstress.infra.results.L_Result;

/**
 * ArrayListAddTest.
 */
@JCStressTest
@State
@Outcome(id = "Hello", expect = Expect.ACCEPTABLE)
@Outcome(id = "World", expect = Expect.ACCEPTABLE)
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
    void arbiter(L_Result result) {
        result.r1 = list.get(1);
    }

}
