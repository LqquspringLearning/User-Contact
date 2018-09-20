package com.thoughtworks.grad.step.Repository;

import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(int id) {
        return UserStorage.get(id);
    }

    @Override
    public User createContact(User user) {
        return UserStorage.createContact(user);
    }

    @Override
    public User updateContact(int id, User dbUser) {
        return UserStorage.update(id, dbUser);
    }

    @Override
    public void deleteContact(int contactId, User user) {
        UserStorage.deleteContact(contactId, user);
    }
}
