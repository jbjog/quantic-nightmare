package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.Playable;
import com.noname.qn.service.Player;

public class HoleSquare extends BasicSquare {
    public HoleSquare(int x,int y) {
        super(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        unhide();
        return Playable.State.LOOSE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return new Texture("hole_square.png");
    }
}
