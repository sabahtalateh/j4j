package com.sabahtalateh.jcstress.accounts_storage.adding;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

/**
 * ConcurrentAddingSameId.
 */
public class ConcurrentAddingSameId {
    @State
    public static class StorageState {
        AccountStorage storage = new AccountStorage();
    }

    @JCStressTest
    @Description("When add account with same id in different thread" +
            "then only one of them should be added.")
    @Outcome(id = "true, false", expect = Expect.ACCEPTABLE)
    @Outcome(id = "false, true", expect = Expect.ACCEPTABLE)
    public static class ConcurrentAddingSameIdTest {
        Account acc1 = new Account("1", "Vova", 100);
        Account acc2 = new Account("1", "Ivan", 200);

        @Actor
        void adder1(StorageState state, ZZ_Result result) {
            state.storage.add(acc1);
            result.r1 = state.storage.get("1") == acc1;
        }

        @Actor
        void adder2(StorageState state, ZZ_Result result) {
            state.storage.add(acc2);
            result.r2 = state.storage.get("1") == acc2;
        }
    }
}
