package com.halversondm.cloud.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void defaultConstructor_createsEmptyCustomer() {
        Customer customer = new Customer();
        assertThat(customer.getId()).isNull();
        assertThat(customer.getCustomerId()).isNull();
        assertThat(customer.getName()).isNull();
        assertThat(customer.getType()).isNull();
    }

    @Test
    void fullConstructor_setsAllFields() {
        Customer customer = new Customer(1, 12345, "John Doe", CustomerType.INDIVIDUAL);
        assertThat(customer.getId()).isEqualTo(1);
        assertThat(customer.getCustomerId()).isEqualTo(12345);
        assertThat(customer.getName()).isEqualTo("John Doe");
        assertThat(customer.getType()).isEqualTo(CustomerType.INDIVIDUAL);
    }

    @Test
    void setters_updateFields() {
        Customer customer = new Customer();
        customer.setId(2);
        customer.setCustomerId(99999);
        customer.setName("Acme Corp");
        customer.setType(CustomerType.BUSINESS);

        assertThat(customer.getId()).isEqualTo(2);
        assertThat(customer.getCustomerId()).isEqualTo(99999);
        assertThat(customer.getName()).isEqualTo("Acme Corp");
        assertThat(customer.getType()).isEqualTo(CustomerType.BUSINESS);
    }

    @Test
    void customerType_hasExpectedValues() {
        assertThat(CustomerType.values()).containsExactlyInAnyOrder(CustomerType.INDIVIDUAL, CustomerType.BUSINESS);
    }
}
