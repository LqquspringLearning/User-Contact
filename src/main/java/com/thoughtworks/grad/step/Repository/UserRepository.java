package com.thoughtworks.grad.step.Repository;

import com.thoughtworks.grad.step.Beans.User;

public interface UserRepository {
    User getById(int id);

    User createContact(User user);

    User updateContact(int id, User dbUser);

    void deleteContact(int contactId, User user);
}
