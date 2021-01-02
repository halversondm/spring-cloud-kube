package com.halversondm.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private List<Account> accounts;

    private static final Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    public AccountController() {
        accounts = new ArrayList<>();
        accounts.add(new Account(1, 1, "111111"));
        accounts.add(new Account(2, 2, "222222"));
        accounts.add(new Account(3, 3, "333333"));
        accounts.add(new Account(4, 4, "444444"));
        accounts.add(new Account(5, 1, "555555"));
        accounts.add(new Account(6, 2, "666666"));
        accounts.add(new Account(7, 2, "777777"));
    }

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET, produces = "application/json")
    public Account findByAccountId(@PathVariable("accountId") String accountId) {
        logger.info(String.format("Account.findByNumber(%s)", accountId));
        return accounts.stream().filter(it -> it.getNumber().equals(accountId)).findFirst().get();
    }

    @RequestMapping(value ="/accounts/customer/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public List<Account> findByCustomerId(@PathVariable("customerId") Integer customerId) {
        logger.info(String.format("Account.findByCustomer(%s)", customerId));
        return accounts.stream().filter(it -> it.getCustomerId().intValue() == customerId.intValue()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = "application/json")
    public List<Account> findAllAccounts() {
        logger.info("Account.findAllAccounts()");
        return accounts;
    }
}
