package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class HoleSquare extends BasicSquare {

    public static final Texture TEXTURE_HOLE_SQUARE = new Texture("hole_square.png");

    public HoleSquare(int x, int y) {
        super(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        reveal();
        return Playable.State.LOOSE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return TEXTURE_HOLE_SQUARE;
    }
}
