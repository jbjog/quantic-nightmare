package com.noname.qn.screen;

import com.noname.qn.hud.OptionMenuHud;
import com.noname.qn.service.gui.Gamable;

public class OptionMenuScreen extends QNScreen {

    public OptionMenuScreen(final Gamable game) {
        super(game);
        hud = new OptionMenuHud(this);
    }
}
