package com.vaos.store.api.service;

import com.vaos.store.api.Model.UserModel;
import com.vaos.store.api.entity.User;
import com.vaos.store.api.entity.VerificationToken;
import com.vaos.store.api.repository.UserRepository;
import com.vaos.store.api.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken
                = new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid token";
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            // make sure to delete the expired token from the repository
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        // we can get the User from verificationToken because
        // @OneToOne (fetch = FetchType.EAGER relationship
        User user = verificationToken.getUser();

        user.setEnabled(true);
        userRepository.save(user);

        return "valid";
    }
}
