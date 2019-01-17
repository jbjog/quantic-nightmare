package com.noname.qn.entity;

import com.noname.qn.service.Enterable;

import java.util.List;

public abstract class Switcher {
    List<Enterable> enterables;
    public List<Enterable> getEnterables() {
        return enterables;
    }

    public Switcher(List<Enterable> enterables) {
        this.enterables = enterables;
    }

    public abstract void switchEnterables();

}
