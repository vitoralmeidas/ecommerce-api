package com.vaos.store.api.event.listener;

import com.vaos.store.api.entity.User;
import com.vaos.store.api.event.RegistrationCompleteEvent;
import com.vaos.store.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        //Send mail to user
        String url =
                event.getApplicationUrl()
                        + "verifyRegistration?token="
                        + token;
        //sendVerificationEmail() --> to actually send the email to the user
        log.info("Click the link to verify your account: {}",
                url);
    }
}
