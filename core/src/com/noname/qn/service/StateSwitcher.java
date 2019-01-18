package com.noname.qn.service;

public interface StateSwitcher extends Stated{
    void switchState(Player.State state);
}
