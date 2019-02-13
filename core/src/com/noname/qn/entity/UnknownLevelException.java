package com.noname.qn.entity;

import com.noname.qn.service.domain.Levelable;

public class UnknownLevelException extends Exception {
    public UnknownLevelException(String message, Levelable.Levels levelNumber) {
        super(message+" : "+levelNumber.toString());
    }
    public UnknownLevelException(Levelable.Levels levelNumber) {
        this("Unknown Level",levelNumber);
    }
}
