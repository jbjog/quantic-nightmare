package com.noname.qn.service;

public interface Enterable extends Hideable,Movable {
    Playable.State enter(Player p);
}
