package com.noname.qn.service;

public interface Player extends Movable, StateSwitcher{
    enum State {
        WAVE,CORPUSCULE
    }
}
