package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LJ_Result;

/**
 * AccountStorageUpdateTest.
 */
@Description("Test that after updates in simultaneously executing threads the state is consistent.")
@JCStressTest
@State
@Outcome(id = "Ivan, 0", expect = Expect.ACCEPTABLE)
@Outcome(id = "Petr, 10", expect = Expect.ACCEPTABLE)
@Outcome(id = "Vova, 20", expect = Expect.ACCEPTABLE)
public class AccountStorageUpdateTest {

    private Account acc1 = new Account("1", "Ivan", 0);
    private AccountStorage storage = new AccountStorage() {{
        add(acc1);
    }};

    @Actor
    void actor1() {
        storage.update(new Account("1", "Petr", 10));
    }

    @Actor
    void actor2() {
        storage.update(new Account("1", "Vova", 20));
    }

    @Arbiter
    void arbiter(LJ_Result result) {
        result.r1 = storage.findByAccountId("1").get().getOwnerName();
        result.r2 = storage.findByAccountId("1").get().getAmount();
    }
}
