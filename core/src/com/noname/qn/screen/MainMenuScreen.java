package com.noname.qn.screen;

import com.noname.qn.hud.MainMenuHud;
import com.noname.qn.service.gui.Gamable;

public class MainMenuScreen extends QNScreen {

    public MainMenuScreen(final Gamable game) {
        super(game);
        hud = new MainMenuHud(this);
    }
}
