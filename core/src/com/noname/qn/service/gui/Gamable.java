package com.noname.qn.service.gui;

public interface Gamable extends Batcher,ScreenChanger, LevelLoader {
    enum Difficulty{
        EASY,NORMAL,NIGHTMARE
    }
}
