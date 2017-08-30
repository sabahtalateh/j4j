package com.sabahtalateh.jcstress.examples;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;
import org.openjdk.jcstress.infra.results.Z_Result;

import java.util.BitSet;

/**
 * BitSetTest.
 */
@Description("Test bit set.")
@JCStressTest
@State
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE)
@Outcome(id = "false, true", expect = Expect.ACCEPTABLE_INTERESTING)
@Outcome(id = "true, false", expect = Expect.ACCEPTABLE_INTERESTING)
public class BitSetTest {

    private BitSet bs = new BitSet();

    @Actor
    void actor1() {
        bs.set(1);
    }

    @Actor
    void actor2() {
        bs.set(2);
    }

    @Arbiter
    void arbiter(ZZ_Result result) {
        result.r1 = bs.get(1);
        result.r2 = bs.get(2);
    }
}
