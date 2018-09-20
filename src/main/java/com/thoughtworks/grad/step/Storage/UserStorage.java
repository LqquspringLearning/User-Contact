package com.thoughtworks.grad.step.Storage;

import com.thoughtworks.grad.step.Beans.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserStorage {


    private static Map<Integer, User> userList = initUserData();

    private static Map<Integer, User> initUserData() {
        Map<Integer, User> result = new LinkedHashMap<>();
        result.put(1, new User(1, "caoyue"));
        result.put(2, new User(2, "zuopeixi"));
        result.put(3, new User(3, "liuyanping"));
        result.put(4, new User(4, "xuya"));
        result.put(5, new User(5, "sunming"));
        return result;
    }

    public static Map<Integer, User> getUserList() {
        return userList;
    }

    public static void add(User user) {
        userList.put(user.getId(), user);
    }

    public static void clear() {
        userList.clear();
    }

    public static User get(int id) {
        return userList.get(id);
    }

    public static User updateOrCreateContact(User user) {
        User dbUser = userList.get(user.getId());
        dbUser.setContacts(user.getContactsMap());
        userList.put(dbUser.getId(), dbUser);
        return userList.get(user.getId());
    }
}
