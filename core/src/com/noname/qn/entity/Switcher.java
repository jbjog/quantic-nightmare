package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.square.BasicSquare;
import com.noname.qn.service.Enterable;

import java.util.ArrayList;
import java.util.List;

public class Switcher {
    List<Enterable> enterables;
    public List<Enterable> getEnterables() {
        return enterables;
    }

    public Switcher(List<Enterable> enterables) {
        this.enterables = enterables;
    }

    public void switchEnterables(){
        Position p0 = enterables.get(0).getPosition();

        for (int i = 0; i < enterables.size()-1; i++) {
            enterables.get(i).setPosition(enterables.get(i+1).getPosition());
        }
        enterables.get(enterables.size()-1).setPosition(p0);
    }

}
