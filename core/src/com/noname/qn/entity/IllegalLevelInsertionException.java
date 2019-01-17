package com.noname.qn.entity;

public class IllegalLevelInsertionException extends Throwable {
    public IllegalLevelInsertionException(Position position) {
        super("Illegal Level Insertion at position : ("+position.getX()+","+position.getY()+")");
    }
}
