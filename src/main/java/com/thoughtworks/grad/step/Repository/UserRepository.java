package com.thoughtworks.grad.step.Repository;

import com.thoughtworks.grad.step.Beans.User;

public interface UserRepository {
    User getById(int id);

    User updateContact(User user);
}
