package com.ECI.book.library.domain.exceptions;

public class UsersException extends LibraryException {

    public UsersException(String message) {
        super(message);
    }


    public static class UserNotFoundException extends UsersException {
        public UserNotFoundException() {
            super("The user does not exist");
        }
    }
}
