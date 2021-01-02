package com.halversondm.cloud.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private Integer id;
    private Integer customerId;
    private String name;
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    public Customer() {

    }

    public Customer(Integer id, Integer customerId, String name, CustomerType type) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
