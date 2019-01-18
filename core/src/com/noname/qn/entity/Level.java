package com.noname.qn.entity;

import com.noname.qn.service.Conditionable;
import com.noname.qn.service.Playable;
import com.noname.qn.service.Player;
import com.noname.qn.service.Tracker;
import com.noname.qn.service.Positionable;
import com.noname.qn.service.Enterable;
import com.noname.qn.service.Movable.Direction;


import java.util.ArrayList;
import java.util.List;

public class Level implements Playable, Tracker {
    private int nbRows, nbColumns;
    private Player player;
    private Conditionable startCondition;
    private Conditionable endCondition;
    private List<Positionable> tracker= new ArrayList<>();

    private List<Switcher> switchers = new ArrayList<>();

    private List<Enterable> squares = new ArrayList<>();
    public List<Enterable> getSquares() {
        return squares;
    }
    public void addSquare(Enterable square) throws IllegalLevelInsertionException{
        if(isPositionOutOfLevel(square))
            throw new IllegalLevelInsertionException(square);
        for(Enterable e : squares){
            if (e.getPosition().hashCode()==square.getPosition().hashCode())
                throw new IllegalLevelInsertionException(square);
        }
        squares.add(square);
    }


    public Player getPlayer() {
        return player;
    }
    public int getNbRows() {
        return nbRows;
    }
    public int getNbColumns() {
        return nbColumns;
    }


    public List<Switcher> getSwitchers() {
        return switchers;
    }
    public void addSwitcher(Switcher s) throws IllegalLevelInsertionException{
        for (Enterable e:s.getEnterables()) {
            addSquare(e);
        }
        switchers.add(s);
    }

    @Override
    public List<Positionable> getTracker() {
        return tracker;
    }

    public Level(int nbRows, int nbColumns, Player startPlayer , Conditionable endCondition){
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;
        this.player = startPlayer;
        this.startCondition = LevelCondition.createCondition(startPlayer.getPosition(),startPlayer.getState());
        this.endCondition = endCondition;
        reset();
    }

    @Override
    public void reset(){
        tracker.clear();
        player.setPosition(startCondition.getPosition());
        player.switchState(startCondition.getState());
        track(player);
    }

    private void track(Positionable p){
        tracker.add(p);
    }

    @Override
    public Playable.State play(Direction d){
        player.move(d);
        if(isPositionOutOfLevel(player))
            return Playable.State.LOOSE;
        Enterable entered = null;
        for(Enterable e : squares){
            if (e.getPosition().hashCode()== player.getPosition().hashCode())
                entered=e;
        }
        Playable.State result = entered.enter(player);
        if(entered.getPosition().equals(endCondition.getPosition()))
            result = Playable.State.WIN;
        for (Switcher s : switchers) {
            s.switchEnterables();
        }
        return result;
    }

    private boolean isPositionOutOfLevel(Positionable p){
        return p.getPosition().getX() >= nbColumns || p.getPosition().getX() <= -1 ||
                p.getPosition().getY() >= nbRows || p.getPosition().getY() <= -1;
    }
}
