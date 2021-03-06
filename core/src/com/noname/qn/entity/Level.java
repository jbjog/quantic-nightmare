package com.noname.qn.entity;

import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.service.domain.*;
import com.noname.qn.service.domain.Movable.Direction;


import java.util.ArrayList;
import java.util.List;

public class Level implements Levelable {
    private int nbRows, nbColumns,levelNumber,bestResult;
    private Player player;
    private Conditionable startCondition;
    private Conditionable endCondition;
    private List<Turn>  tracker= new ArrayList<>();
    private List<Switcher> switchers = new ArrayList<>();
    private List<Enterable> squares = new ArrayList<>();
    private int minMoves;
    private String name;
    public Level(int levelNumber, int nbRows, int nbColumns, Player startPlayer , Conditionable endCondition){
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;
        this.player = startPlayer;
        this.startCondition = LevelCondition.createCondition(startPlayer.getPosition(),startPlayer.getDuality());
        this.endCondition = endCondition;
        this.levelNumber = levelNumber;
        minMoves=0;
        name="";
        bestResult = 0;
    }

    @Override
    public List<Enterable> getSquares() {
        return squares;
    }

    @Override
    public void addSquare(Enterable square) throws IllegalLevelInsertionException{
        if(isPositionOutOfLevel(square))
            throw new OutOfBoundsLevelInsertionException(square,nbRows,nbColumns);
        for(Enterable e : squares){
            if (e.getPosition().hashCode()==square.getPosition().hashCode())
                throw new DuplicatedLevelInsertionException(square);
        }
        if(square.getPosition().equals(startCondition.getPosition()))
            square.reveal();
        squares.add(square);
    }

    @Override
    public int getMinimumMoves() {
        return minMoves;
    }
    @Override
    public void setMinimumMoves(int min) {
        minMoves = min;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public int getBestResult() {
        return bestResult;
    }

    @Override
    public void setBestResult(int r) {
        bestResult = r;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public int getNbRows() {
        return nbRows;
    }

    @Override
    public int getNbColumns() {
        return nbColumns;
    }

    @Override
    public void addSwitcher(Switcher s) throws IllegalLevelInsertionException{
        for (Enterable e:s.getEnterables()) {
            addSquare(e);
        }
        switchers.add(s);
    }

    @Override
    public List<Turn>  getTracker() {
        return tracker;
    }

    @Override
    public void reset(){
        tracker.clear();
        player.setPosition(startCondition.getPosition());
        player.switchDuality(startCondition.getDuality());
        switch (QNPreferences.getPref().getDifficulty()){
            case EASY:
                for (Enterable e: getSquares()) {
                    if(!e.getPosition().equals(startCondition.getPosition()))
                        e.reveal();
                }
                break;
            case NORMAL:
                break;
            case NIGHTMARE:
                for (Enterable e: getSquares()) {
                    if(!e.getPosition().equals(startCondition.getPosition()))
                        e.hide();
                }
                break;
        }
    }

    @Override
    public State getLastState() {
        if(getTracker().size()>0)
            return getTracker().get(getTracker().size()-1).getState();
        return State.CONTINUE;
    }

    @Override
    public Playable.State play(Direction d){
        player.move(d);
        if(isPositionOutOfLevel(player)) {
            track(d,Playable.State.LOOSE);
            return Playable.State.LOOSE;
        }
        Enterable entered = null;
        for(Enterable e : squares){
            if (e.getPosition().hashCode()== player.getPosition().hashCode())
                entered=e;
        }
        Playable.State result = entered != null ? entered.enter(player) : State.LOOSE;
        for (Switcher s : switchers) {
            s.switchEnterables();
        }
        track(d,result);
        return result;
    }

    private void track(Direction d, State s){
        tracker.add(new Turn(d,s));
    }

    private boolean isPositionOutOfLevel(Positionable p){
        return p.getPosition().getX() >= nbColumns || p.getPosition().getX() <= -1 ||
                p.getPosition().getY() >= nbRows || p.getPosition().getY() <= -1;
    }
}
