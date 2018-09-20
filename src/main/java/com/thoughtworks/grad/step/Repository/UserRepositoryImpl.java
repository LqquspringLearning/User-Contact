package com.thoughtworks.grad.step.Repository;

import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Storage.UserStorage;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(int id) {
      return  UserStorage.get(id);
    }

    @Override
    public User updateContact(User user) {
        return UserStorage.updateContact(user);
    }
}
