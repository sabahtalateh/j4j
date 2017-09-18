package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.JJ_Result;

/**
 * TransferDeadlock.
 */
public class TransferDeadlock {
    @State
    public static class StorageState {
        final AccountStorage storage = new AccountStorage() {{
            add(new Account("1", "Ivan", 3L));
            add(new Account("2", "Nick", 5L));
        }};
    }

    @JCStressTest
    @Outcome(id = "5, 3", expect = Expect.ACCEPTABLE)
    public static class TransferDeadlockTest {
        @Actor
        void from1To2(StorageState state) {
            state.storage.transfer("1", "2", 3L);
        }

        @Actor
        void from2To1(StorageState state) {
            state.storage.transfer("2", "1", 5L);
        }

        @Arbiter
        void arbiter(StorageState state, JJ_Result result) {
            result.r1 = state.storage.get("1").getAmount();
            result.r2 = state.storage.get("2").getAmount();
        }
    }
}
