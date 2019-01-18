package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.Conditionable;
import com.noname.qn.service.Playable;
import com.noname.qn.service.Player;

public class ArrivalSquare extends BasicSquare {
    private Conditionable c;
    public ArrivalSquare(Conditionable c) {
        super(c.getPosition());
        this.c = c;
    }
    @Override
    public Playable.State enter(Player p) {
        unhide();
        if(p.getState()==c.getState())
            return Playable.State.WIN;
        else
            return Playable.State.LOOSE;
    }

    @Override
    public Texture getTexture() {
        if (c.getState() == Player.State.CORPUSCULE)
            return new Texture("arrival_corpuscule_square.png");
        else
            return new Texture("arrival_wave_square.png");
    }
}
