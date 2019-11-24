package com.spec.analysis.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("User \'" + username + "\' was not found.");
    }

}
