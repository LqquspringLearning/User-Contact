package com.thoughtworks.grad.step.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.Gender;
import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        UserStorage.clear();
    }

    @Test
    void should_create_update_a_user_contact() throws Exception {
        UserStorage.add(new User(1, "caoyue"));
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        mockMvc.perform(post("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contacts", hasSize(1)))
                .andExpect(jsonPath("$.contacts[0].id").value(1))
                .andExpect(jsonPath("$.contacts[0].name").value("huanglizhen"))
                .andExpect(jsonPath("$.contacts[0].gender").value("female"));

    }

    private static String convertToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}