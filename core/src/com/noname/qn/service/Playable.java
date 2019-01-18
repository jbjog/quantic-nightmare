package com.noname.qn.service;

public interface Playable {
    enum State {
        WIN,LOOSE,CONTINUE
    }
    Playable.State play(Movable.Direction d);
    void reset();
}
