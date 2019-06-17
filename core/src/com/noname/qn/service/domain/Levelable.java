package com.noname.qn.service.domain;

import com.noname.qn.entity.IllegalLevelInsertionException;

import java.util.List;

public interface Levelable extends Tracker,Playable {
    enum Levels{
        TEST, LEVEL_1
    }
    int getNbRows();
    int getNbColumns();
    Player getPlayer();
    void addSwitcher(Switcher s)throws IllegalLevelInsertionException;
    List<Enterable> getSquares();
    void addSquare(Enterable square) throws IllegalLevelInsertionException;
}
