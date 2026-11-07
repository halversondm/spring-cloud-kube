package com.halversondm.cloud.controller;

import com.halversondm.cloud.AccountApplication;
import com.halversondm.cloud.data.Account;
import com.halversondm.cloud.data.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    private final AccountData accountData;

    public AccountController(AccountData accountData) {
        this.accountData = accountData;
    }

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET, produces = "application/json")
    public Account findByAccountId(@PathVariable("accountId") String accountId) {
        Integer id = Integer.valueOf(accountId);
        logger.info("Account.findByAccountId({})", id);
        Optional<Account> account = accountData.findById(id);
        return account.orElse(null);
    }

    @RequestMapping(value = "/accounts/customer/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public List<Account> findByCustomerId(@PathVariable("customerId") Integer customerId) {
        logger.info(String.format("Account.findByCustomer(%s)", customerId));
        return accountData.findByCustomerId(customerId);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = "application/json")
    public List<Account> findAllAccounts() {
        logger.info("Account.findAllAccounts()");
        return accountData.findAccounts();
    }
}
