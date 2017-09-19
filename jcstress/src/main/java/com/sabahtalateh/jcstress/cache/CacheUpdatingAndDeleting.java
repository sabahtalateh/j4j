package com.sabahtalateh.jcstress.cache;

import com.sabahtalateh.j4j.multithreading.non_blocking.cache.Cache;
import com.sabahtalateh.j4j.multithreading.non_blocking.cache.Model;
import com.sabahtalateh.j4j.multithreading.non_blocking.cache.OptimisticUpdateException;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LZZ_Result;
import org.openjdk.jcstress.infra.results.ZZZ_Result;
import org.openjdk.jcstress.infra.results.ZZ_Result;

/**
 * StorageState.
 */
public class CacheUpdatingAndDeleting {

    @State
    public static class StorageState {
        Cache cache = new Cache() {{
            add("1", new Model("First"));
        }};
    }

    @Outcome(id = "true, true, false", expect = Expect.ACCEPTABLE, desc = "(removed, updated, no exceptions during update)")
    @Outcome(id = "true, false, true", expect = Expect.ACCEPTABLE, desc = "(removed, not updated, exceptions during update)")
    @Outcome(id = "true, false, false", expect = Expect.ACCEPTABLE, desc = "(removed, not updated, no exceptions while updating)")
    @JCStressTest
    public static class CacheUpdatingAndDeletingTest {

        @Actor
        void updater1(StorageState state, ZZZ_Result result) {
            result.r1 = state.cache.delete("1");
        }

        @Actor
        void updater2(StorageState state, ZZZ_Result result) {
            try {
                result.r2 = state.cache.update("1", new Model("Hello"));
            } catch (OptimisticUpdateException e) {
                result.r3 = true;
            }
        }
    }
}
