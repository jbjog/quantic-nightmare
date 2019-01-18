package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.Playable;
import com.noname.qn.service.Player;

public class TPSquare extends BasicSquare {
    Position outPosition;
    public TPSquare(Position position,Position outPosition) {
        super(position);
        this.outPosition = outPosition;
    }

    @Override
    public Playable.State enter(Player p) {
        unhide();
        p.setPosition(outPosition);
        return Playable.State.CONTINUE;
    }

    @Override
    protected Texture getSpecificTexture() {
        return new Texture("tp_square.png");
    }

}
