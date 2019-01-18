package com.noname.qn.screen;

import com.noname.qn.hud.StageMenuHud;
import com.noname.qn.service.gui.Gamable;

public class StageMenuScreen extends QNScreen {

    public StageMenuScreen(final Gamable game) {
        super(game);
        hud = new StageMenuHud(this);
    }
}
