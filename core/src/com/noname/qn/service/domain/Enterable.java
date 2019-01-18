package com.noname.qn.service.domain;

public interface Enterable extends Hideable,Movable {
    Playable.State enter(Player p);
}
