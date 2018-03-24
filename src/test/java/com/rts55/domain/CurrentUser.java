package com.rts55.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private static ThreadLocal<User> users = new ThreadLocal<>();

    @Autowired
    private UserBuilder userBuilder;

    public void set(User user) {users.set(user);}

    public User get() {
        User user = users.get();
        if (null ==  user) {
            users.set(userBuilder.build());
        }
        return users.get();
    }
}
