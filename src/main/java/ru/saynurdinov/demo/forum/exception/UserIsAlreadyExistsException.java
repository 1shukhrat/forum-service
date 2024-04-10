package ru.saynurdinov.demo.forum.exception;

public class UserIsAlreadyExistsException extends RuntimeException {
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
