package com.halversondm.cloud.controller;

import com.halversondm.cloud.client.AccountClient;
import com.halversondm.cloud.data.Customer;
import com.halversondm.cloud.data.CustomerData;
import com.halversondm.cloud.dto.CustomerAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerData customerData;

    @Autowired
    private AccountClient accountClient;

    public CustomerController() {
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public CustomerAccount findByCustomerId(@PathVariable("customerId") Integer customerId) {
        logger.info(String.format("Customer.findByCustomerId(%s)", customerId));
        CustomerAccount customerAccount = new CustomerAccount();
        Optional<Customer> customer = customerData.findCustomerByCustomerId(customerId);
        customerAccount.setCustomer(customer.orElse(null));
        customerAccount.setAccountList(accountClient.getAccounts(customerId));
        return customerAccount;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> findAll() {
        logger.info("Customer.findAll()");
        return customerData.findCustomers();
    }
}
