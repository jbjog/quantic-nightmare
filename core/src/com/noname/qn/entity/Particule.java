package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.Movable;
import com.noname.qn.service.Texturable;

public class Particule implements Movable, Texturable {
    private Position position;
    private State state;

    public Particule(State state, Position position) {
        this.state = state;
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

    public State getState() {
        return state;
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
        if(this.state == State.CORPUSCULE)
            return new Texture("particule.png");
        else
            return new Texture("wave.png");
    }

    public void switchState(){
        if (state == State.CORPUSCULE)
            state = State.WAVE;
        else
            state = State.CORPUSCULE;
    }

    public enum State {
        WAVE,CORPUSCULE
    }
}
