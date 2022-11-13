package com.example.demo.api.exceptions;

public class BadRequestBody extends RuntimeException{

    public BadRequestBody() {
    }

    public BadRequestBody(String message) {
        super(message);
    }

    public BadRequestBody(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestBody(Throwable cause) {
        super(cause);
    }
}
