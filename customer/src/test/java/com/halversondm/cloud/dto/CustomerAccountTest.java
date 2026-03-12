package com.halversondm.cloud.dto;

import com.halversondm.cloud.client.Account;
import com.halversondm.cloud.data.Customer;
import com.halversondm.cloud.data.CustomerType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerAccountTest {

    @Test
    void settersAndGetters_workCorrectly() {
        Customer customer = new Customer(1, 12345, "John Doe", CustomerType.INDIVIDUAL);
        List<Account> accounts = List.of(
                new Account(1, 12345, "500.00"),
                new Account(2, 12345, "200.00")
        );

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAccountList(accounts);

        assertThat(customerAccount.getCustomer()).isEqualTo(customer);
        assertThat(customerAccount.getAccountList()).hasSize(2);
        assertThat(customerAccount.getAccountList()).isEqualTo(accounts);
    }

    @Test
    void defaultConstructor_createsEmptyObject() {
        CustomerAccount customerAccount = new CustomerAccount();
        assertThat(customerAccount.getCustomer()).isNull();
        assertThat(customerAccount.getAccountList()).isNull();
    }
}
