package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.service.domain.Conditionable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.domain.Player;

public class ArrivalSquare extends BasicSquare {
    public static final Texture TEXTURE_ARRIVAL_CORP = new Texture("arrival_corpuscule_square.png");
    public static final Texture TEXTURE_ARRIVAL_WAVE = new Texture("arrival_wave_square.png");
    private Conditionable c;
    public ArrivalSquare(Conditionable c) {
        super(c.getPosition().getX(),c.getPosition().getY());
        this.c = c;
    }
    @Override
    public Playable.State enter(Player p) {
        reveal();
        return p.getDuality() == c.getDuality() ? Playable.State.WIN : Playable.State.LOOSE;
    }

    @Override
    public Texture getTexture() {
        return c.getDuality() == Player.Duality.CORPUSCULE ? TEXTURE_ARRIVAL_CORP : TEXTURE_ARRIVAL_WAVE;
    }
}
