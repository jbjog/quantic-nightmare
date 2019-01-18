package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Position;
import com.noname.qn.service.Playable;
import com.noname.qn.service.Player;

public class SwitchStateSquare extends BasicSquare {
    public SwitchStateSquare(int x,int y) {
        super(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        unhide();
        if (p.getState() == Player.State.CORPUSCULE)
            p.switchState(Player.State.WAVE);
        else
            p.switchState(Player.State.CORPUSCULE);
        return Playable.State.CONTINUE;
    }

    @Override
    public Texture getTexture() {
        return new Texture("switch_state_square.png");
    }
}
