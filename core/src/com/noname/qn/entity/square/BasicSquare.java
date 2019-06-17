package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class BasicSquare implements Enterable {
    public static final Texture TEXTURE_BASIC_SQUARE = new Texture("basic_square.png");
    public static final Texture TEXTURE_HIDDEN_SQUARE = new Texture("hidden_square.png");
    private Position position;
    public BasicSquare(int x,int y) {
        this.position = new Position(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        reveal();
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
    public void reveal() {
        hidden = false;
    }

    @Override
    public void hide() {
        hidden = true;
    }


    protected Texture getSpecificTexture() {
        return TEXTURE_BASIC_SQUARE;
    }

    @Override
    public Texture getTexture() {
        if (isHidden())
            return TEXTURE_HIDDEN_SQUARE;
        else
            return getSpecificTexture();
    }
}
