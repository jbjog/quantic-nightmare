package com.noname.qn.screen;

import com.noname.qn.hud.LevelHud;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.gui.Gamable;

public class LevelScreen extends QNScreen {

    public LevelScreen(final Gamable game,Levelable level) {
        super(game);
        hud = new LevelHud(this,level);
    }
}
