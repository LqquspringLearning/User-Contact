package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.Gender;
import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserContactCreateTest extends UserControllerTest {
    @Test
    void should_create_a_user_contact() throws Exception {
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        UserStorage.add(new User(1, "caoyue"));
        mockMvc.perform(post("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contacts.1.id").value(1))
                .andExpect(jsonPath("$.contacts.1.name").value("huanglizhen"))
                .andExpect(jsonPath("$.contacts.1.gender").value("female"));
    }

    @Test
    void should_get_bad_request_when_use_id_less_0() throws Exception {
        UserStorage.add(new User(0, "caoyue"));
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        mockMvc.perform(post("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(contact)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_bad_request_when_contact_is_empty() throws Exception {
        UserStorage.add(new User(1, "caoyue"));
        mockMvc.perform(post("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""))
                .andExpect(status().isBadRequest());
    }


}
