package com.noname.qn.service;

public interface Movable extends Positionable{
    int UP=0;
    int DOWN=1;
    int LEFT=2;
    int RIGHT=3;
    void move(int direction);
}
