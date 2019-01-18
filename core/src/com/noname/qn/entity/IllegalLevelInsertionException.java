package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;

public class IllegalLevelInsertionException extends Exception {
    public IllegalLevelInsertionException(String message,Positionable p) {
        super(message+" : ("+p.getPosition().getX()+","+p.getPosition().getY()+")");
    }
    public IllegalLevelInsertionException(Positionable p) {
        this("Illegal Level Insertion at position",p);
    }
}
