package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;

public class TPSquare extends BasicSquare {
    Position outPosition;
    public TPSquare(Position position,Position outPosition) {
        super(position);
        this.outPosition = outPosition;
    }

    @Override
    public Level.State enter(Particule particule) {
        unhide();
        particule.setPosition(outPosition);
        return Level.State.CONTINUE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return new Texture("tp_square.png");
    }

}