package com.noname.qn.service.domain;

import com.noname.qn.entity.Position;

public interface Movable extends Positionable{
    enum Direction{
        UP,DOWN,LEFT,RIGHT
    }
    void move(Direction direction);
    void setPosition(Position p);
}
