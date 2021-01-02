package com.halversondm.cloud.client;

public class Account {

    private Integer id;
    private Integer customerId;
    private String amount;

    public Account() {

    }

    public Account(Integer id, Integer customerId, String amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
