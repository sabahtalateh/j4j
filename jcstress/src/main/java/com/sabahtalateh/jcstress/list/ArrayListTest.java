package com.sabahtalateh.jcstress.list;

import com.sabahtalateh.j4j.multithreading.list.ArrayList;
import com.sabahtalateh.j4j.multithreading.list.List;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.L_Result;

/**
 * ArrayListTest.
 */
@JCStressTest
@State
public class ArrayListTest {

    List<Long> list = new ArrayList<>();

    @Actor
    void actor1() {
        list.add(0x8000000000000000L);
    }

    @Actor
    void actor2() {
        list.add(0x7fffffffffffffffL);
    }

    @Arbiter
    void arbiter(L_Result result) {
        result.r1 = list.get(0);
    }

}
