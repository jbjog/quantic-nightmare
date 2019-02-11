package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Texturable;
import com.noname.qn.utils.Pair;


public class Turn extends Pair<Movable.Direction, Playable.State> implements Texturable {
    public Turn(Movable.Direction direction, Playable.State state) {
        super(direction, state);
    }

    @Override
    public Texture getTexture() {
        String arrow = "";
        switch (this.getElement1()){
            case DOWN:
                arrow="down";
                break;
            case UP:
                arrow="up";
                break;
            case LEFT:
                arrow="left";
                break;
            case RIGHT:
                arrow="right";
                break;
        }
        if(this.getElement2()== Playable.State.LOOSE)
            arrow=arrow+"_red";
        arrow = arrow+".png";
        return new Texture(arrow);
    }
}
