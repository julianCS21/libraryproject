package com.ECI.book.library.domain.exceptions;

public class LendsException extends LibraryException {

    public LendsException(String message) {
        super(message);
    }


    public static class BookNotFoundException extends LendsException {
        public BookNotFoundException() {
            super("The book does not exist");
        }
    }

    public static class BookNotAvailableException extends LendsException {
        public BookNotAvailableException() {
            super("The book is not available at the moment");
        }
    }

    public static class PendingPenalitiesException extends LendsException {
        public PendingPenalitiesException() {
            super("The user has pending penalities");
        }
    }
}
