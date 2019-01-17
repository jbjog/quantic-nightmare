package com.noname.qn.entity.square;

import com.badlogic.gdx.graphics.Texture;
import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;
import com.noname.qn.entity.Position;

public class ArrivalSquare extends BasicSquare {
    Particule.State arrivalState;
    public ArrivalSquare(Position position, Particule.State state) {
        super(position);
        this.arrivalState = state;
    }
    @Override
    public Level.State enter(Particule particule) {
        unhide();
        if(particule.getState()==arrivalState)
            return Level.State.WIN;
        else
            return Level.State.LOOSE;
    }

    @Override
    public Texture getTexture() {
        if (arrivalState == Particule.State.CORPUSCULE)
            return new Texture("arrival_corpuscule_square.png");
        else
            return new Texture("arrival_wave_square.png");
    }
}
