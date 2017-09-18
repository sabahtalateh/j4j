package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.*;

/**
 * UpdateWhileTransfer.
 */
public class UpdateWhileTransfer {
    @State
    public static class StorageState {
        final AccountStorage storage = new AccountStorage() {{
            add(new Account("1", "Ivan", 20L));
            add(new Account("2", "Nick", 30L));
        }};
    }

    @JCStressTest
    public static class UpdateWhileTransferTest {
        @Actor
        void transfer(StorageState state, ZJJ_Result result) {
            synchronized (state.storage) {
                result.r1 = state.storage.transfer("1", "2", 10L);
                result.r2 = state.storage.get("1").getAmount();
                result.r3 = state.storage.get("2").getAmount();
            }

//            result.r1 = state.storage.get("1").getAmount();
//            result.r2 = state.storage.get("2").getAmount();
        }

        @Actor
        void update(StorageState state) {
//            synchronized (state.storage) {
                state.storage.update(new Account("1", "Ivan", 5L));
//            }
//            result.r3 = state.storage.get("1").getAmount();
        }

//        @Arbiter
//        void arbiter(StorageState state, ZJJ_Result result) {
//
//        }
    }
}
