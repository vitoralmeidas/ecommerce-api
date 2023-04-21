package com.vaos.store.api.service;

import com.vaos.store.api.entity.VerificationToken;
import com.vaos.store.api.model.UserModel;
import com.vaos.store.api.entity.User;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordRestTokenForUser(User user, String token);

    String validatePasswordRestToken(String token);
}
