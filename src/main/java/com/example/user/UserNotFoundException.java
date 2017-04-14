package com.example.user;

/**
 * Created by jihun.im on 2017-04-06.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}
