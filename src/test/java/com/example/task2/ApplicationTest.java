package com.example.task2;

import com.example.task2.rest.RegisterRequest;
import com.example.task2.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {


    final String URI = "/userservice/register";

    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void registerTest1() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .firstName("Gonenc")
                .lastName("Dogan")
                .password("123")
                .userName("gnnc").build();

        mvc.perform(MockMvcRequestBuilders
                .post(URI)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").exists());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void registerTest2() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .firstName("Gonenc")
                .lastName("Dogan")
                .password("123")
                .userName("gnnc").build();

        mvc.perform(MockMvcRequestBuilders
                .post(URI)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value(Constants.USER_ALREADY_EXISTS_CODE));
    }

    @Test
    public void registerTest3() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .firstName("Seda")
                .lastName("Dogan")
                .password("1234")
                .userName("seda").build();

        mvc.perform(MockMvcRequestBuilders
                .post(URI)
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

