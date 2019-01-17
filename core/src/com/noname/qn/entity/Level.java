package com.noname.qn.entity;

import com.noname.qn.entity.square.ArrivalSquare;
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
        if(squares.containsKey(square.getPosition()) || !isPositionInLevel(square.getPosition()))
            throw new IllegalLevelInsertionException(square.getPosition());
        squares.put(square.getPosition(),square);
    }

    private int nbRows, nbColumns;

    public Particule getParticule() {
        return particule;
    }
    public int getNbRows() {
        return nbRows;
    }
    public int getNbColumns() {
        return nbColumns;
    }

    private Position startPosition;
    ArrivalSquare arrival;
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

    public Level(int nbRows, int nbColumns, Position startPosition, ArrivalSquare arrival) throws IllegalLevelInsertionException{
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;
        this.startPosition = startPosition;
        this.arrival = arrival;
        addSquare(arrival);
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
        if(isPositionInLevel(particule.getPosition()))
            return State.LOOSE;
        Enterable entered = squares.get(particule.getPosition());
        Level.State result = entered.enter(particule);
        if(entered.getPosition().equals(arrival.getPosition()))
            result = State.WIN;
        for (Switcher s : switchers) {
            s.switchEnterables();
        }
        return result;
    }

    public enum State {
        WIN,LOOSE,CONTINUE
    }

    private boolean isPositionInLevel(Position p){
        return p.getX()<nbRows && p.getX()>-1 && p.getY()<nbColumns && p.getY()>-1;
    }
}
