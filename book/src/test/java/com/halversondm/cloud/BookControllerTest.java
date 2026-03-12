package com.halversondm.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void available_returnsSpringInAction() throws Exception {
        mockMvc.perform(get("/available"))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring in action"));
    }

    @Test
    void checkedOut_returnsSpringBootInAction() throws Exception {
        mockMvc.perform(get("/checked-out"))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Boot in Action"));
    }
}
