package com.noname.qn;


import com.noname.qn.entity.*;
import com.noname.qn.entity.square.ArrivalSquare;
import com.noname.qn.entity.square.BasicSquare;
import com.noname.qn.entity.square.HoleSquare;
import com.noname.qn.entity.square.TPSquare;
import com.noname.qn.service.Enterable;
import com.noname.qn.service.Movable;

public class QNGameTest {

    private static Level createLevelTest() throws IllegalLevelInsertionException {
        Level level = new Level(3,4,new Position(0,0),new ArrivalSquare(new Position(2,2), Particule.State.CORPUSCULE));
        BasicSquare b1 = new BasicSquare(new Position(0,0));
        level.addSquare(b1);
        BasicSquare b2 = new BasicSquare(new Position(0,1));
        level.addSquare(b2);
        BasicSquare b3 = new TPSquare(new Position(0,2),new Position(1,0));
        level.addSquare(b3);
        BasicSquare b4 = new BasicSquare(new Position(0,3));
        level.addSquare(b4);


        b1 = new BasicSquare(new Position(1,0));
        level.addSquare(b1);
        b2 = new BasicSquare(new Position(1,1));
        level.addSquare(b2);
        b3 = new HoleSquare(new Position(1,2));
        level.addSquare(b3);
        b4 = new HoleSquare(new Position(1,3));
        level.addSquare(b4);


        b1 = new BasicSquare(new Position(2,0));
        level.addSquare(b1);
        b2 = new BasicSquare(new Position(2,1));
        level.addSquare(b2);
        b4 = new HoleSquare(new Position(2,3));
        level.addSquare(b4);


        return level;
    }

    private static void displayLevel(Level l,Level.State st){
        for (int i = 0; i < l.getNbRows(); i++) {
            for (int k = 0; k < 3 ; k++) {
                for (int j = 0; j < l.getNbColumns(); j++) {
                    displayEnterable(l,l.getSquares().get(new Position(i,j)),k);
                }
                System.out.println("");
            }
        }
        System.out.println("*************************************************");
        System.out.println(st);
        System.out.println("*************************************************");
    }
    private static void displayEnterable(Level l, Enterable enterable, int lg) {
        String s = "-";
        if(enterable.getPosition().equals(l.getParticule().getPosition()))
            s="+";
        if(enterable instanceof ArrivalSquare)
            s="*";
        if(enterable instanceof TPSquare)
            s="/";
        if(enterable instanceof HoleSquare)
            s="o";
        switch (lg){
            case 1:
                System.out.print("   "+"|");
                break;
            default:
                System.out.print(s+s+s+"|");
        }
    }

    public static void main (String []args){
        try {
            testLevelWin();
            testLevelLoose();
        } catch (IllegalLevelInsertionException e) {
            e.printStackTrace();
        }

    }

    private static void testLevelWin() throws IllegalLevelInsertionException {
        Level l = createLevelTest();
        l.play(Movable.RIGHT);
        l.play(Movable.RIGHT);
        l.play(Movable.RIGHT);
        l.play(Movable.DOWN);
        Level.State state = l.play(Movable.RIGHT);
        assertLevelState(state, Level.State.WIN);
    }
    private static void testLevelLoose() throws IllegalLevelInsertionException {
        System.out.println("testLevelLoose");
        Level l = createLevelTest();
        l.play(Movable.RIGHT);
        l.play(Movable.RIGHT);
        l.play(Movable.RIGHT);
        Level.State state = l.play(Movable.RIGHT);
        assertLevelState(state, Level.State.LOOSE);
    }

    private static void assertLevelState(Level.State got, Level.State expected) {
        if (got != expected) {
            throw new RuntimeException("Level state should be "+expected+" but got : " + got);
        }
    }

    private static void testWinLevel() {

    }
}