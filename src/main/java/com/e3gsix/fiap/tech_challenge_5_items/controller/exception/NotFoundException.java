package com.e3gsix.fiap.tech_challenge_5_items.controller.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
