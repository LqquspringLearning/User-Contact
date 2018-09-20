package com.thoughtworks.grad.step.Storage;

import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.Gender;
import com.thoughtworks.grad.step.Beans.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserStorage {


    private static Map<Integer, User> userList = initUserData();

    public static Map<Integer, User> initUserData() {
        Map<Integer, User> result = new LinkedHashMap<>();
        result.put(1, new User(1, "caoyue"));
        result.put(2, new User(2, "zuopeixi"));
        result.put(3, new User(3, "liuyanping"));
        result.put(4, new User(4, "xuya"));
        result.put(5, new User(5, "sunming"));
        return result;
    }

    public static Map<Integer, User> initUserDataWithContact() {
        Map<Integer, Contact> contactMap = new HashMap<>();
        Contact contact = new Contact(1, "huanglizhen", "13212332121", 18, Gender.female);
        contactMap.put(contact.getId(), contact);
        Contact anotherContact = new Contact(2, "zuopeixi", "12345678910", 18, Gender.male);
        contactMap.put(anotherContact.getId(), anotherContact);
        Map<Integer, User> result = new LinkedHashMap<>();
        result.put(1, new User(1, "caoyue", contactMap));
        result.put(2, new User(2, "zuopeixi", contactMap));
        result.put(3, new User(3, "liuyanping", contactMap));
        result.put(4, new User(4, "xuya", contactMap));
        result.put(5, new User(5, "sunming", contactMap));
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

    public static User createContact(User user) {
        User dbUser = userList.get(user.getId());
        dbUser.setContacts(user.getContactsMap());
        userList.put(dbUser.getId(), dbUser);
        return userList.get(user.getId());
    }

    public static void init(Map<Integer, User> initData) {
        userList = initData;
    }

    public static User update(int id, User user) {
        User dbUser = userList.get(id);
        dbUser.setContacts(user.getContactsMap());
        userList.put(id, dbUser);
        return userList.get(user.getId());
    }

    public static void deleteContact(int contactId, User user) {
        User dbUser = userList.get(user.getId());
        Map<Integer, Contact> contactMap = dbUser.getContactsMap();
        contactMap.remove(contactId);
        dbUser.setContacts(contactMap);
        userList.put(dbUser.getId(), dbUser);
    }
}
