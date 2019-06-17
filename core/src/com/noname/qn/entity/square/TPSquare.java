package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class TPSquare extends BasicSquare {
    public static final Texture TEXTURE_TP_SQUARE = new Texture("tp_square.png");
    Enterable outSquare;
    public TPSquare(int x,int y,Enterable outSquare) {
        super(x,y);
        this.outSquare = outSquare;
    }

    @Override
    public Playable.State enter(Player p) {
        reveal();
        p.setPosition(outSquare.getPosition());
        return outSquare.enter(p);
    }

    @Override
    protected Texture getSpecificTexture() {
        return TEXTURE_TP_SQUARE;
    }

}
