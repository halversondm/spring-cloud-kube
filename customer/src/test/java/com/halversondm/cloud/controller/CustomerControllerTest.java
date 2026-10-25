package com.halversondm.cloud.controller;

import com.halversondm.cloud.client.Account;
import com.halversondm.cloud.client.AccountClient;
import com.halversondm.cloud.data.Customer;
import com.halversondm.cloud.data.CustomerData;
import com.halversondm.cloud.data.CustomerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerData customerData;

    @MockitoBean
    private AccountClient accountClient;

    @Test
    void findByCustomerId_returnsCustomerWithAccounts() throws Exception {
        Customer customer = new Customer(1, 12345, "John Doe", CustomerType.INDIVIDUAL);
        List<Account> accounts = List.of(
                new Account(1, 12345, "500.00"),
                new Account(2, 12345, "200.00")
        );
        when(customerData.findCustomerByCustomerId(12345)).thenReturn(Optional.of(customer));
        when(accountClient.getAccounts(12345)).thenReturn(accounts);

        mockMvc.perform(get("/customers/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer.customerId").value(12345))
                .andExpect(jsonPath("$.customer.name").value("John Doe"))
                .andExpect(jsonPath("$.accountList.length()").value(2))
                .andExpect(jsonPath("$.accountList[0].amount").value("500.00"));
    }

    @Test
    void findByCustomerId_returnsNullCustomer_whenNotFound() throws Exception {
        when(customerData.findCustomerByCustomerId(99999)).thenReturn(Optional.empty());
        when(accountClient.getAccounts(99999)).thenReturn(List.of());

        mockMvc.perform(get("/customers/99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").doesNotExist())
                .andExpect(jsonPath("$.accountList").isEmpty());
    }

    @Test
    void findAll_returnsAllCustomers() throws Exception {
        List<Customer> customers = List.of(
                new Customer(1, 12345, "John Doe", CustomerType.INDIVIDUAL),
                new Customer(2, 12346, "Acme Corp", CustomerType.BUSINESS)
        );
        when(customerData.findCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Acme Corp"))
                .andExpect(jsonPath("$[1].type").value("BUSINESS"));
    }

    @Test
    void findAll_returnsEmptyList_whenNoCustomers() throws Exception {
        when(customerData.findCustomers()).thenReturn(List.of());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
