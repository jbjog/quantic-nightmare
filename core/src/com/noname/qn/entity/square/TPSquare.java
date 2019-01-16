package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;
import com.noname.qn.service.Enterable;

public class TPSquare extends BasicSquare {
    Position outPosition;
    public TPSquare(Position position,Position outPosition) {
        super(position);
        this.outPosition = outPosition;
    }

    @Override
    public void enter(Particule particule) {
        unhide();
        particule.setPosition(outPosition);
    }

    @Override
    protected Texture getSpecificTexture() {
        return new Texture("tp_square.png");
    }

}
