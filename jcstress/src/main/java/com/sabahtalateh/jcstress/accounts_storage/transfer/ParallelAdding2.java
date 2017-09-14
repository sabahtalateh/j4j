package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.JJ_Result;
import org.openjdk.jcstress.infra.results.LL_Result;
import org.openjdk.jcstress.infra.results.L_Result;
import org.openjdk.jcstress.infra.results.ZZ_Result;

public class ParallelAdding2 {

    @State
    public static class StorageState {
        final AccountStorage storage = new AccountStorage();
    }

    @Description("Aa and BB has same hashcode, so in multithreading environment putting into map will cause" +
            "null pointer exception because of they will be placed in same bucket.")
    @Outcome(id ="A, B", expect = Expect.ACCEPTABLE, desc = "Both accounts were added.")
    @JCStressTest
    static class ParallelAdding2Test {
        @Actor
        void actor1(StorageState state) {
            state.storage.add(new Account("Aa", "A", 10));
        }

        @Actor
        void actor2(StorageState state) {
            state.storage.add(new Account("BB", "B", 20));
        }

        @Arbiter
        void arbiter(StorageState state, LL_Result result) {
            result.r1 = state.storage.get("Aa").getOwnerName();
            result.r2 = state.storage.get("BB").getOwnerName();
        }
    }
}
