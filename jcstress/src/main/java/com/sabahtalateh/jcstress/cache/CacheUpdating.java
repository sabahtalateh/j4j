package com.sabahtalateh.jcstress.cache;

import com.sabahtalateh.j4j.multithreading.non_blocking.cache.Cache;
import com.sabahtalateh.j4j.multithreading.non_blocking.cache.Model;
import com.sabahtalateh.j4j.multithreading.non_blocking.cache.OptimisticUpdateException;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LZZ_Result;

/**
 * StorageState.
 */
public class CacheUpdating {

    @State
    public static class StorageState {
        Cache cache = new Cache() {{
            add("1", new Model("First"));
        }};
    }

    @JCStressTest
    @Outcome(id = "Hello, false, false", expect = Expect.ACCEPTABLE, desc = "No exceptions, updater1 thread came after updater2.")
    @Outcome(id = "World, false, false", expect = Expect.ACCEPTABLE, desc = "No exceptions, updater2 thread came after updater1.")
    @Outcome(id = "World, true, false", expect = Expect.ACCEPTABLE, desc = "updater1 occured exception.")
    @Outcome(id = "Hello, false, true", expect = Expect.ACCEPTABLE, desc = "updater2 occured exception.")
    public static class CacheUpdatingTest {

        @Actor
        void updater1(StorageState state, LZZ_Result result) {
            try {
                state.cache.update("1", new Model("Hello"));
            } catch (OptimisticUpdateException e) {
                result.r2 = true;
            }
        }

        @Actor
        void updater2(StorageState state, LZZ_Result result) {
            try {
                state.cache.update("1", new Model("World"));
            } catch (OptimisticUpdateException e) {
                result.r3 = true;
            }
        }

        @Arbiter
        void arbiter(StorageState state, LZZ_Result result) {
            result.r1 = state.cache.get("1").getName();
        }
    }
}
