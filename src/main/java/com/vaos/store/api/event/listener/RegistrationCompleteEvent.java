package com.vaos.store.api.event.listener;

import com.vaos.store.api.entity.User;
import com.vaos.store.api.event.RegistrationComplentEvent;
import com.vaos.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEvent implements ApplicationListener<RegistrationComplentEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationComplentEvent event) {
        // create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        //Send mail to user
    }
}
