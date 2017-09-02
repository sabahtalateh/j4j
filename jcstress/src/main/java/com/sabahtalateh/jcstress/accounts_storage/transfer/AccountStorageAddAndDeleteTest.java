package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

/**
 * AccountStorageAddAndUpdateTest.
 */
@JCStressTest
@State
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE)
@Outcome(id = "true, false", expect = Expect.ACCEPTABLE)
@Outcome(id = "false, true", expect = Expect.ACCEPTABLE)
@Outcome(id = "false, false", expect = Expect.ACCEPTABLE)
public class AccountStorageAddAndDeleteTest {

    private AccountStorage storage = new AccountStorage();

    @Actor
    void actor1() {
        storage.add(new Account("1", "Ivan", 100));

    }

    @Actor
    void actor2() {
        storage.delete(new Account("1", "Ivan", 100));

    }

    @Actor
    void actor3(ZZ_Result result) {
        result.r1 = storage.findByAccountId("1").isPresent();
        result.r2 = storage.findByAccountId("1").isPresent();
    }
}
