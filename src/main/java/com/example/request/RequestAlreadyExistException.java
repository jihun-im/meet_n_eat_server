package com.example.request;

/**
 * Created by Jihun on 2017-04-14.
 */
public class RequestAlreadyExistException extends RuntimeException{
    public RequestAlreadyExistException(long requestId) {
        super("requestId already exist: '" + requestId + "'.");
    }
}
