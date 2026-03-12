package com.halversondm.cloud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halversondm.cloud.data.Account;
import com.halversondm.cloud.data.AccountData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountData accountData;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByAccountId_returnsAccount_whenFound() throws Exception {
        Account account = new Account(1, 12345, "500.00");
        when(accountData.findById(1)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerId").value(12345))
                .andExpect(jsonPath("$.amount").value("500.00"));
    }

    @Test
    void findByAccountId_returnsNull_whenNotFound() throws Exception {
        when(accountData.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/accounts/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void findByCustomerId_returnsAccountList() throws Exception {
        List<Account> accounts = List.of(
                new Account(1, 12345, "500.00"),
                new Account(2, 12345, "200.00")
        );
        when(accountData.findByCustomerId(12345)).thenReturn(accounts);

        mockMvc.perform(get("/accounts/customer/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void findByCustomerId_returnsEmptyList_whenNoAccounts() throws Exception {
        when(accountData.findByCustomerId(99999)).thenReturn(List.of());

        mockMvc.perform(get("/accounts/customer/99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void findAllAccounts_returnsAllAccounts() throws Exception {
        List<Account> accounts = List.of(
                new Account(1, 12345, "500.00"),
                new Account(2, 12346, "300.00"),
                new Account(3, 12347, "750.00")
        );
        when(accountData.findAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }
}
