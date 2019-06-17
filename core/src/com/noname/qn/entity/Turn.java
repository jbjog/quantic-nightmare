package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Texturable;


public class Turn implements Texturable {
    private Movable.Direction d;
    private Playable.State s;

    public Movable.Direction getDirection() {
        return d;
    }

    public Playable.State getState() {
        return s;
    }

    public Turn(Movable.Direction direction, Playable.State state) {
        d = direction;
        s = state;
    }

    @Override
    public Texture getTexture() {
        String arrow = "";
        switch (this.d){
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
        if(this.s == Playable.State.LOOSE)
            arrow=arrow+"_red";
        arrow = arrow+".png";
        return new Texture(arrow);
    }
}
