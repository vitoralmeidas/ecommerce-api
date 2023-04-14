package com.vaos.store.api.event;

import com.vaos.store.api.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationComplentEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    public RegistrationComplentEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
