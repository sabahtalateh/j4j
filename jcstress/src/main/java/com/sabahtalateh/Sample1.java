package com.sabahtalateh;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * Sample1.
 */
@JCStressTest
@State
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Both threads read 1.")
@Outcome(id = "1, 2", expect = Expect.ACCEPTABLE, desc = "Second actor greater then first.")
@Outcome(id = "2, 1", expect = Expect.ACCEPTABLE, desc = "First actor greater then second.")
public class Sample1 {

    volatile int v = 0;

    @Actor
    public void actor1(II_Result result) {
        result.r1 = ++v;
    }

    @Actor
    public void actor2(II_Result result) {
        result.r2 = ++v;
    }
}
