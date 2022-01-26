package com.example.robwarehouse.exception;

public class PositionQuantityNotEnoughException extends RuntimeException {

    public PositionQuantityNotEnoughException(String msg) {
        super(msg);
    }
}

