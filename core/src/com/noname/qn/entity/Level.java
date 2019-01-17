package com.noname.qn.entity;

import com.noname.qn.entity.square.ArrivalSquare;
import com.noname.qn.service.Enterable;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Particule particule;

    private List<Enterable> squares = new ArrayList<>();
    public List<Enterable> getSquares() {
        return squares;
    }

    public void addSquare(Enterable square) throws IllegalLevelInsertionException{
        if(!isPositionInLevel(square.getPosition()))
            throw new IllegalLevelInsertionException(square.getPosition());
        for(Enterable e : squares){
            if (e.getPosition().hashCode()==square.getPosition().hashCode())
                throw new IllegalLevelInsertionException(square.getPosition());
        }
        squares.add(square);
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

    private List<Switcher> switchers = new ArrayList<>();
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
        if(!isPositionInLevel(particule.getPosition()))
            return State.LOOSE;
        Enterable entered = null;
        for(Enterable e : squares){
            if (e.getPosition().hashCode()==particule.getPosition().hashCode())
                entered=e;
        }
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
        return p.getX()<nbColumns&& p.getX()>-1 && p.getY()<nbRows  && p.getY()>-1;
    }
}
