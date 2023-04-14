package com.vaos.store.api.service;

import com.vaos.store.api.Model.UserModel;
import com.vaos.store.api.entity.User;

public interface UserService {
    public User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
