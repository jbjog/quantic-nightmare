package com.noname.qn.entity;

public class IllegalLevelInsertionException extends Throwable {
    public IllegalLevelInsertionException(Position position) {
        super("Level already has an Enterable at position : ("+position.getX()+","+position.getY()+")");
    }
}
