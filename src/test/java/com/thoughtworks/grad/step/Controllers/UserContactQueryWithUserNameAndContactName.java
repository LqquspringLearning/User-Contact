package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Storage.UserStorage;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserContactQueryWithUserNameAndContactName extends UserControllerTest {
    @Test
    void should_get_user_contact_by_user_name_and_contact_name() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "caoyue")
                .param("contactName", "zuopeixi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zuopeixi"))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    void should_get_404_when_user_name_not_exists() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "zhangsan")
                .param("contactName", "zuopeixi"))
                .andExpect(status().isNotFound());

    }
    @Test
    void should_get_404_when_contact_name_not_exists() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "caoyue")
                .param("contactName", "zhangsan"))
                .andExpect(status().isNotFound());

    }
    @Test
    void should_get_404_when_uesr_name_contact_name_both_not_exists() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "lisi")
                .param("contactName", "zhangsan"))
                .andExpect(status().isNotFound());

    }
    @Test
    void should_get_400_when_contact_name_is_empty() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "caoyue")
                .param("contactName", ""))
                .andExpect(status().isBadRequest());

    }
    @Test
    void should_get_400_when_user_name_is_empty() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "")
                .param("contactName", "zuopeixi"))
                .andExpect(status().isBadRequest());

    }
    @Test
    void should_get_400_when_user_name_contact_name_both_empty() throws Exception {
        UserStorage.init(UserStorage.initUserDataWithContact());
        mockMvc.perform(get("/api/user/")
                .param("userName", "")
                .param("contactName", ""))
                .andExpect(status().isBadRequest());

    }
}
