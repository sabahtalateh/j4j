package com.sabahtalateh.jcstress.counter;

import com.sabahtalateh.j4j.multithreading.synchronization.Counter;
import com.sabahtalateh.j4j.multithreading.synchronization.SyncCounter;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/**
 * SyncCounterTest.
 */
@JCStressTest
@State
@Outcome(id = "2", expect = Expect.ACCEPTABLE)
public class SyncCounterTest {
    Counter counter = new SyncCounter();

    @Actor
    void actor1() {
        counter.increment();
    }

    @Actor
    void actor2() {
        counter.increment();
    }

    @Arbiter
    void arbiter(J_Result result) {
        result.r1 = counter.getValue();
    }
}
