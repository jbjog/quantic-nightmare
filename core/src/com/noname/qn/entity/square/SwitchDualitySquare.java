package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class SwitchDualitySquare extends BasicSquare {
    public SwitchDualitySquare(int x, int y) {
        super(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        unhide();
        if (p.getDuality() == Player.Duality.CORPUSCULE)
            p.switchDuality(Player.Duality.WAVE);
        else
            p.switchDuality(Player.Duality.CORPUSCULE);
        return Playable.State.CONTINUE;
    }

    @Override
    public Texture getTexture() {
        return new Texture("switch_state_square.png");
    }
}
