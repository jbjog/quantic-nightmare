package com.noname.qn.entity;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Player;

public class Particule implements Player {
    private Position position;
    private Duality duality;

    public Particule(Duality duality, Position position) {
        this.duality = duality;
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
    public Duality getDuality() {
        return duality;
    }

    @Override
    public void move(Direction d) {
        switch (d){
            case UP:
                position.setY(position.getY()-1);
                break;
            case DOWN:
                position.setY(position.getY()+1);
                break;
            case LEFT:
                position.setX(position.getX()-1);
                break;
            case RIGHT:
                position.setX(position.getX()+1);
                break;
        }
    }

    @Override
    public Texture getTexture() {
        if(this.duality == Duality.CORPUSCULE)
            return new Texture("particule.png");
        else
            return new Texture("wave.png");
    }

    @Override
    public void switchDuality(Duality duality){
        this.duality = duality;
    }

}
