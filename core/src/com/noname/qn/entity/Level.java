package com.noname.qn.entity;

import com.noname.qn.service.Enterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
    private Particule particule;

    private Map<Position, Enterable> squares = new HashMap<Position,Enterable>();
    public Map<Position, Enterable> getSquares() {
        return squares;
    }

    public void addSquare(Enterable square) throws IllegalLevelInsertionException{
        if(squares.containsKey(square.getPosition()))
            throw new IllegalLevelInsertionException(square.getPosition());
        squares.put(square.getPosition(),square);
    }

    private int nbRows, nbColumns;
    private Position startPosition;
    private Position endPosition;
    private Particule.State ps;

    private List<Switcher> switchers;
    public List<Switcher> getSwitchers() {
        return switchers;
    }
    public void addSwitcher(Switcher s) throws IllegalLevelInsertionException{
        for (Enterable e:s.getEnterables()) {
            addSquare(e);
        }
        switchers.add(s);
    }

    private List<Position> tracker= new ArrayList<Position>();
    public List<Position> getTracker() {
        return tracker;
    }

    public Level(int nbRows, int nbColumns, Position startPosition, Position endPosition, Particule.State ps) {
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        reset();
    }

    public void reset(){
        tracker.clear();
        particule = new Particule(ps,startPosition);
        track(startPosition);
    }

    private void track(Position p){
        tracker.add(p);
    }

    public Level.State play(int direction){
        particule.move(direction);
        Enterable entered = squares.get(particule.getPosition());
        Level.State result = entered.enter(particule);
        if(entered.getPosition().equals(endPosition))
            result = State.WIN;
        for (Switcher s : switchers) {
            s.switchEnterables();
        }
        return result;
    }

    public enum State {
        WIN,LOOSE,CONTINUE
    }
}
