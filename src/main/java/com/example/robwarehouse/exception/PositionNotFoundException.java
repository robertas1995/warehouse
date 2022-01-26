package com.example.robwarehouse.exception;

public class PositionNotFoundException extends RuntimeException {

    public PositionNotFoundException(Long id) {
        super("Position not found by ID: " + id);
    }

    public PositionNotFoundException(String msg) {
        super(msg);
    }
}