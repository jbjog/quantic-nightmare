package com.noname.qn.service.domain;

public interface Player extends Movable, DualitySwitcher,Texturable{
    enum Duality {
        WAVE,CORPUSCULE
    }
}
