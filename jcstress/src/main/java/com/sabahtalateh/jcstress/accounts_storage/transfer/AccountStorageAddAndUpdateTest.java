package com.sabahtalateh.jcstress.accounts_storage.transfer;

import com.sabahtalateh.j4j.multithreading.accounts.Account;
import com.sabahtalateh.j4j.multithreading.accounts.AccountStorage;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZLJ_Result;

import java.util.Optional;

/**
 * AccountStorageAddAndUpdateTest.
 */
@JCStressTest
@State
@Outcome(id = "true, Ivan, 100", expect = Expect.ACCEPTABLE)
@Outcome(id = "true, Vova, 500", expect = Expect.ACCEPTABLE)
public class AccountStorageAddAndUpdateTest {

    private AccountStorage storage = new AccountStorage();

    @Actor
    void actor1() {
        storage.add(new Account("1", "Ivan", 100));
    }

    @Actor
    void actor2() {
        storage.update(new Account("1", "Vova", 500));
    }

    @Arbiter
    void arbiter(ZLJ_Result result) {
        Account account = storage.get("1");
        result.r1 = account != null;
        result.r2 = "None";
        result.r3 = 0;
        if (result.r1) {
            result.r2 = account.getOwnerName();
            result.r3 = account.getAmount();
        }
    }
}
