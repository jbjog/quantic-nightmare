package com.noname.qn.entity;

import com.noname.qn.service.Positionable;

public class IllegalLevelInsertionException extends Throwable {
    public IllegalLevelInsertionException(Positionable p) {
        super("Illegal Level Insertion at position : ("+p.getPosition().getX()+","+p.getPosition().getY()+")");
    }
}
