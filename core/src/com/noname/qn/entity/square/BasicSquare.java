package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class BasicSquare implements Enterable {
    private Position position;
    public BasicSquare(int x,int y) {
        this.position = new Position(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        unhide();
        return Playable.State.CONTINUE;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void setPosition(Position p) {
        position = new Position(p.getX(),p.getY());
    }

    private boolean hidden = true;
    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void unhide() {
        hidden = false;
    }

    @Override
    public void hide() {
        hidden = true;
    }


    protected Texture getSpecificTexture() {
        return new Texture("basic_square.png");
    }

    @Override
    public Texture getTexture() {
        if (isHidden())
            return new Texture("hidden_square.png");
        else
            return getSpecificTexture();
    }
}
