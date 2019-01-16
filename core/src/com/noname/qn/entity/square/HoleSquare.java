package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;

public class HoleSquare extends BasicSquare {
    public HoleSquare(Position position) {
        super(position);
    }

    @Override
    public Level.State enter(Particule particule) {
        unhide();
        return Level.State.LOOSE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return new Texture("hole_square.png");
    }
}
