package com.noname.qn.service;

public interface Player extends Movable, StateSwitcher,Texturable{
    enum State {
        WAVE,CORPUSCULE
    }
}
