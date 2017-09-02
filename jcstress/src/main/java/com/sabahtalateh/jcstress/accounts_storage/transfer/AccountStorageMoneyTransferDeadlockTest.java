package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LL_Result;

/**
 * AccountStorageMoneyTransferTest.
 */
@Description("Test that deadlock will not occure when both accounts will" +
        "transfer money to each other simultaneously in different threads.")
@JCStressTest
@State
@Outcome(id = "5, 10", expect = Expect.ACCEPTABLE)
@Outcome(id = "10, 5", expect = Expect.ACCEPTABLE)
@Outcome(id = "0, 15", expect = Expect.ACCEPTABLE)
@Outcome(id = "15, 0", expect = Expect.ACCEPTABLE)
public class AccountStorageMoneyTransferDeadlockTest {

    private Account acc1 = new Account("1", "Ivan", 5);
    private Account acc2 = new Account("2", "Petr", 10);
    private AccountStorage storage = new AccountStorage() {{
        add(acc1);
        add(acc2);
    }};

    @Actor
    void actor1() {
        storage.transfer("1", "2", 5);
    }

    @Actor
    void actor2() {
        storage.transfer("2", "1", 10);
    }

    @Arbiter
    void arbiter(LL_Result result) {
        result.r1 = acc1.getAmount();
        result.r2 = acc2.getAmount();
    }
}
