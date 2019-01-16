package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;
import com.noname.qn.service.Enterable;

public class BasicSquare implements Enterable {
    private Position position;
    public BasicSquare(Position position) {
        this.position = position;
    }

    @Override
    public Level.State enter(Particule particule) {
        unhide();
        return Level.State.CONTINUE;
    }

    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public void setPosition(Position aPosition) {}

    private boolean hidden = true;
    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void unhide() {
        hidden = false;
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
