package com.noname.qn.entity;

public class UnknownLevelException extends Exception {
    public UnknownLevelException(String message, int levelNumber) {
        super(message+" : "+levelNumber);
    }
    public UnknownLevelException(int levelNumber) {
        this("Unknown Level",levelNumber);
    }
}
