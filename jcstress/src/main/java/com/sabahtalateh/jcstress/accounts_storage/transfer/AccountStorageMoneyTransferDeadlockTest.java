package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LLL_Result;
import org.openjdk.jcstress.infra.results.LL_Result;

/**
 * AccountStorageMoneyTransferTest.
 */
@Description("Account storage transfer test.")
@JCStressTest
@State
@Outcome(id = "1, 0, 10", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 10, 0", expect = Expect.ACCEPTABLE)
public class AccountStorageMoneyTransferDeadlockTest {

    private Account acc1 = new Account("1", "Ivan", 20);
    private Account acc2 = new Account("2", "Petr", 20);
    private AccountStorage storage = new AccountStorage() {{
        add(acc1);
        add(acc2);
    }};

    @Actor
    void actor1(LL_Result result) {
        storage.transfer(acc1, acc2, 10);
        result.r1 = acc1.getAmount();
        result.r2 = acc2.getAmount();
    }

    @Actor
    void actor2(LL_Result result) {
        storage.transfer(acc2, acc1, 10);
        result.r2 = acc2.getAmount();
        result.r1 = acc1.getAmount();
    }
}
