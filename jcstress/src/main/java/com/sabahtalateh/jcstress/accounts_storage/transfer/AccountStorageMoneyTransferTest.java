package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.LLL_Result;

/**
 * AccountStorageMoneyTransferTest.
 */
@Description("Account storage transfer test.")
@JCStressTest
@State
@Outcome(id = "1, 0, 10", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 10, 0", expect = Expect.ACCEPTABLE)
public class AccountStorageMoneyTransferTest {

    private Account acc1 = new Account("1", "Ivan", 11);
    private Account acc2 = new Account("2", "Petr", 0);
    private Account acc3 = new Account("3", "Igor", 0);
    private AccountStorage storage = new AccountStorage() {{
        add(acc1);
        add(acc2);
        add(acc3);
    }};

    @Actor
    void actor1(LLL_Result result) {
        storage.transfer(acc1, acc2, 10);
        result.r1 = acc1.getAmount();
        result.r2 = acc2.getAmount();
    }

    @Actor
    void actor2(LLL_Result result) {
        storage.transfer(acc1, acc3, 10);
        result.r1 = acc1.getAmount();
        result.r3 = acc3.getAmount();
    }
}
