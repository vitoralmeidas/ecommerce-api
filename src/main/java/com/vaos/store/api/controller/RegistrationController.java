package com.vaos.store.api.controller;

import com.vaos.store.api.entity.VerificationToken;
import com.vaos.store.api.model.PasswordModel;
import com.vaos.store.api.model.UserModel;
import com.vaos.store.api.entity.User;
import com.vaos.store.api.event.RegistrationCompleteEvent;
import com.vaos.store.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/api/v1/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationURL(request)
        ));

        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User Verifies Successfully";
        }

        return "Bad User";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        //we need to http request to generate the url again
        VerificationToken verificationToken =
                userService.generateNewVerificationToken(oldToken);

        User user = verificationToken.getUser();

        resendVerificationTokenMail(applicationURL(request), verificationToken);

        return "Verification Link Sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {

        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordRestTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationURL(request), token);
        }

        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordRestToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "PASSWORD HAS SAVED.";
        }

        return result;
    }

    private String passwordResetTokenMail(User user, String applicationURL, String token) {
        String url =
                applicationURL
                        + "/savePassword?token="
                        + token;

        log.info("Click the link to Reset the Password!: {} ", url);

        return url;
    }

    private void resendVerificationTokenMail(String applicationURL, VerificationToken verificationToken) {
        String url =
                applicationURL
                        + "verifyRegistration?token="
                        + verificationToken.getToken();

        //sendVerificationEmail() --> to actually send the email to the user
        log.info("Click the link to verify your account: {}",
                url);
    }

    private String applicationURL(HttpServletRequest request) {

        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                "/" +
                request.getContextPath();
    }

}
