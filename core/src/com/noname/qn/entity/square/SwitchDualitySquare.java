package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class SwitchDualitySquare extends BasicSquare {

    public static final Texture TEXTURE_SWITCH_STATE_SQUARE = new Texture("switch_state_square.png");

    public SwitchDualitySquare(int x, int y) {
        super(x,y);
    }

    @Override
    public Playable.State enter(Player p) {
        reveal();
        if (p.getDuality() == Player.Duality.CORPUSCULE)
            p.switchDuality(Player.Duality.WAVE);
        else
            p.switchDuality(Player.Duality.CORPUSCULE);
        return Playable.State.CONTINUE;
    }

    @Override
    public Texture getTexture() {
        return TEXTURE_SWITCH_STATE_SQUARE;
    }

    @Override
    public boolean isHidden() {
        return false;
    }
}
