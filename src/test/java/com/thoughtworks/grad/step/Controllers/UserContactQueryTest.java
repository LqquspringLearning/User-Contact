package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserContactQueryTest extends UserControllerTest {
    @Test
    void should_get_all_contact() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/1/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_get_bad_request_when_user_id_less_1() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/0/contacts"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_bad_request_with_error_msg_when_user_not_exists() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/12/contacts"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("user not exists"));
    }
}
