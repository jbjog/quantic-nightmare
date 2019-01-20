package com.noname.qn.entity;

import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Switcher;

import java.util.List;

public class SquareSwitcher implements Switcher {
    private List<Enterable> enterables;

    public SquareSwitcher(List<Enterable> enterables) {
        this.enterables = enterables;
    }

    @Override
    public List<Enterable> getEnterables() {
        return enterables;
    }

    @Override
    public void switchEnterables(){
        Position p0 = enterables.get(0).getPosition();
        for (int i = 0; i < enterables.size()-1; i++) {
            enterables.get(i).setPosition(enterables.get(i+1).getPosition());
        }
        enterables.get(enterables.size()-1).setPosition(p0);
    }

}
