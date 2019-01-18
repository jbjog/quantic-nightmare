package com.noname.qn.service.domain;

public interface Player extends Movable, StateSwitcher,Texturable{
    enum State {
        WAVE,CORPUSCULE
    }
}
