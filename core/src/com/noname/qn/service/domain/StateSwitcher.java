package com.noname.qn.service.domain;

public interface StateSwitcher extends Stated{
    void switchState(Player.State state);
}
