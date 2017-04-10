package com.example;

/**
 * Created by Jihun on 2017-04-10.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userId) {
        super("user already exist: '" + userId + "'.");
    }
}