package com.noname.qn.service.gui;


public interface ScreenChanger {
    void changeScreen(Type screen);

    enum Type {
        SPLASH,
        HOME,
        PLAY,
        OPTIONS
    }
}
