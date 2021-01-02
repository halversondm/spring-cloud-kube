package com.halversondm.cloud.dto;

import com.halversondm.cloud.client.Account;
import com.halversondm.cloud.data.Customer;

import java.util.List;

public class CustomerAccount {

    private Customer customer;
    private List<Account> accountList;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
