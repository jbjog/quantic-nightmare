package com.noname.qn.entity;

import com.noname.qn.service.Enterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
    private Particule particule;
    private List<Enterable> squares = new ArrayList<Enterable>();
    public List<Enterable> getSquares() {
        return squares;
    }
    public void addSquare(Enterable square){
        squares.add(square);
    }

    private int nbRows, nbColumns;
    private Position startPosition;
    private Position endPosition;
    private Particule.State ps;

    private List<Position> tracker= new ArrayList<Position>();


    public Level(int nbRows, int nbColumns, Position startPosition,  Position endPosition,Particule.State ps) {
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

    private void track(Position aPosition){
        tracker.add(aPosition);
    }

    public enum State {
        WIN,LOOSE,CONTINUE
    }
}
