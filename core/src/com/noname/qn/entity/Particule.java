package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.Movable;
import com.noname.qn.service.Texturable;

public class Particule implements Movable, Texturable {
    private Position position;
    private State stateParticule;

    public Particule(State stateParticule,Position position) {
        this.stateParticule = stateParticule;
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position aPosition) {
        position = aPosition;
    }

    @Override
    public void move(int direction) {
        switch (direction){
            case Movable.UP:
                position.setY(position.getY()-1);
            case Movable.DOWN:
                position.setY(position.getY()+1);
            case Movable.LEFT:
                position.setX(position.getX()-1);
            case Movable.RIGHT:
                position.setX(position.getX()+1);
        }
    }

    @Override
    public Texture getTexture() {
        if(this.stateParticule == State.CORPUSCULE)
            return new Texture("particule.png");
        else
            return new Texture("wave.png");
    }
    public enum State {
        WAVE,CORPUSCULE
    }
}
