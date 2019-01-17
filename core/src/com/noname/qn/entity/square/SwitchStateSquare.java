package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;

public class SwitchStateSquare extends BasicSquare {
    public SwitchStateSquare(Position position) {
        super(position);
    }

    @Override
    public Level.State enter(Particule particule) {
        unhide();
        particule.switchState();
        return Level.State.CONTINUE;
    }

    @Override
    public Texture getTexture() {
        return new Texture("switch_state_square.png");
    }
}
