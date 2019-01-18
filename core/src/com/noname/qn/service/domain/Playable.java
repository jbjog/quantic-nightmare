package com.noname.qn.service.domain;

public interface Playable {
    enum State {
        WIN,LOOSE,CONTINUE
    }
    Playable.State play(Movable.Direction d);
    void reset();
}
