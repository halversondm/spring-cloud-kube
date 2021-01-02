package com.halversondm.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private List<Customer> customers;

    @Autowired
    private AccountClient accountClient;

    public CustomerController() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "12345", "Steve Sanders", CustomerType.INDIVIDUAL));
        customers.add(new Customer(2, "12346", "Monica Lewinsky", CustomerType.INDIVIDUAL));
        customers.add(new Customer(3, "12347", "Bill Clinton", CustomerType.INDIVIDUAL));
        customers.add(new Customer(4, "12348", "Heidi Fleiss", CustomerType.INDIVIDUAL));
    }


    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public Customer findByCustomerId(@PathVariable("customerId") String customerId) {
        logger.info(String.format("Customer.findByCustomerId(%s)", customerId));
        Customer customer = customers.stream().filter(it -> it.getCustomerId().equals(customerId)).findFirst().get();
        List<Account> accounts = accountClient.getAccounts(customer.getId());
        customer.setAccounts(accounts);
        return customer;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> findAll() {
        logger.info("Customer.findAll()");
        return customers;
    }
}
