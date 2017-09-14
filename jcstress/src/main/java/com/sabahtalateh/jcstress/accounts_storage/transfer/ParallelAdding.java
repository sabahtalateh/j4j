package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.*;

public class ParallelAdding {

    @State
    public static class StorageState {
        final AccountStorage storage = new AccountStorage();
    }

    @Outcome(id ="true, true", expect = Expect.ACCEPTABLE, desc = "Arbiter must see all the items added.")
    @JCStressTest
    static class ParallelAddingTest {
        @Actor
        void actor1(StorageState state) {
            state.storage.add(new Account("1", "Ivan", 10));
        }

        @Actor
        void actor2(StorageState state) {
            state.storage.add(new Account("2", "Vova", 20));
        }

        @Arbiter
        void arbiter(StorageState state, ZZ_Result result) {
            result.r1 = state.storage.get("1") != null;
            result.r2 = state.storage.get("2") != null;
        }
    }
}
