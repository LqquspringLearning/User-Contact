package com.thoughtworks.grad.step.Beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;

public class User {


    private int id;
    private String name;

    private Map<Integer, Contact> contacts;

    public User(int id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, Map<Integer, Contact> contacts) {
        this(id, name);
        this.contacts = contacts;
    }

    public User() {
        contacts = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setContacts(Map<Integer, Contact> contacts) {
        this.contacts = contacts;
    }

    public void setContacts(Contact contact) {
        this.contacts.put(contact.getId(), contact);
    }


    public Map<Integer, Contact> getContactsMap() {
        return contacts;
    }

    public Collection<Contact> getContacts() {
        return contacts.values();
    }

}
