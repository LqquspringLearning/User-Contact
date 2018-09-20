package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Storage.UserStorage;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserContactDeleteTest extends UserControllerTest {

    @Test
    void should_delete_user_contact_by_contact_id() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(delete("/api/user/1/contact/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_get_bad_request_when_user_id_less_1() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(delete("/api/user/0/contact/1"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void should_get_bad_request_when_contact_id_less_1() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(delete("/api/user/1/contact/0"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void should_get_not_found_when_user_id_not_exists() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(delete("/api/user/10/contact/1"))
                .andExpect(status().isNotFound());
    }
}

