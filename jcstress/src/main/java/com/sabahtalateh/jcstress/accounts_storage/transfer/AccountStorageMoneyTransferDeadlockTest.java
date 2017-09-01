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
@Outcome(id = "10, 10", expect = Expect.ACCEPTABLE)
@Outcome(id = "0, 20", expect = Expect.ACCEPTABLE)
@Outcome(id = "20, 0", expect = Expect.ACCEPTABLE)
public class AccountStorageMoneyTransferDeadlockTest {

    private Account acc1 = new Account("1", "Ivan", 10);
    private Account acc2 = new Account("2", "Petr", 10);
    private AccountStorage storage = new AccountStorage() {{
        add(acc1);
        add(acc2);
    }};

    @Actor
    void actor1(LL_Result result) {
        storage.transfer("1", "2", 10);
        result.r1 = acc1.getAmount();
        result.r2 = acc2.getAmount();
    }

    @Actor
    void actor2(LL_Result result) {
        storage.transfer("2", "1", 10);
        result.r2 = acc2.getAmount();
        result.r1 = acc1.getAmount();
    }
}
