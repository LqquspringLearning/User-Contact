package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.Gender;
import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserContactUpdateTest extends UserControllerTest {
    @Test
    void should_update_a_user_contact() throws Exception {
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        HashMap<Integer, Contact> mapContact = new HashMap<>();
        mapContact.put(contact.getId(), contact);
        UserStorage.add(new User(1, "caoyue", mapContact));
        assertThat(UserStorage.get(1).getContactsMap().get(1)).isEqualTo(contact);

        Contact updateContact = new Contact(1, "zuopeixi", "13212332121", 18, Gender.male);
        mockMvc.perform(put("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(updateContact)))
                .andExpect(status().isNoContent());

        assertThat(UserStorage.get(1).getContactsMap().get(1)).isEqualTo(updateContact);
    }

    @Test
    void should_create_contact_when_contact_not_exists() throws Exception {

        UserStorage.add(new User(1, "caoyue"));

        Contact contact = new Contact(1, "zuopeixi", "13212332121", 18, Gender.male);
        mockMvc.perform(put("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contacts[0].id").value(1))
                .andExpect(jsonPath("$.contacts[0].name").value("zuopeixi"))
                .andExpect(jsonPath("$.contacts[0].gender").value("male"));
        ;

    }

    @Test
    void should_get_bad_request_when_use_id_less_0() throws Exception {
        UserStorage.add(new User(0, "caoyue"));
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        mockMvc.perform(put("/api/user/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertToJson(contact)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_bad_request_when_contact_is_empty() throws Exception {
        UserStorage.add(new User(1, "caoyue"));
        mockMvc.perform(put("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""))
                .andExpect(status().isBadRequest());
    }
}
