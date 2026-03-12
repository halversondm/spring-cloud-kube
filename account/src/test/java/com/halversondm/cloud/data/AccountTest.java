package com.halversondm.cloud.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void defaultConstructor_createsEmptyAccount() {
        Account account = new Account();
        assertThat(account.getId()).isNull();
        assertThat(account.getCustomerId()).isNull();
        assertThat(account.getAmount()).isNull();
    }

    @Test
    void fullConstructor_setsAllFields() {
        Account account = new Account(1, 12345, "500.00");
        assertThat(account.getId()).isEqualTo(1);
        assertThat(account.getCustomerId()).isEqualTo(12345);
        assertThat(account.getAmount()).isEqualTo("500.00");
    }

    @Test
    void setters_updateFields() {
        Account account = new Account();
        account.setId(2);
        account.setCustomerId(99999);
        account.setAmount("1000.00");

        assertThat(account.getId()).isEqualTo(2);
        assertThat(account.getCustomerId()).isEqualTo(99999);
        assertThat(account.getAmount()).isEqualTo("1000.00");
    }
}
