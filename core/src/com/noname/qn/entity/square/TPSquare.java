package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class TPSquare extends BasicSquare {
    public static final Texture TEXTURE_TP_SQUARE = new Texture("tp_square.png");
    Position outPosition;
    public TPSquare(int x,int y,int xOut,int yOut) {
        super(x,y);
        this.outPosition = new Position(xOut,yOut);
    }

    @Override
    public Playable.State enter(Player p) {
        reveal();
        p.setPosition(outPosition);
        return Playable.State.CONTINUE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return TEXTURE_TP_SQUARE;
    }

}
