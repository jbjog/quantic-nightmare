package com.noname.qn;


import com.noname.qn.entity.*;
import com.noname.qn.entity.square.ArrivalSquare;
import com.noname.qn.entity.square.BasicSquare;
import com.noname.qn.entity.square.HoleSquare;
import com.noname.qn.entity.square.TPSquare;
import com.noname.qn.service.Conditionable;
import com.noname.qn.service.Enterable;
import com.noname.qn.service.Movable;
import com.noname.qn.service.Playable;

import java.util.ArrayList;
import java.util.List;

public class QNGameTest {
    private static void displayLevel(Level l, Playable.State st){
        for (int i = 0; i < l.getNbRows(); i++) {
            for (int k = 0; k < 3 ; k++) {
                for (int j = 0; j < l.getNbColumns(); j++) {
                    for(Enterable e : l.getSquares()){
                        if (e.getPosition().hashCode()==(new Position(j,i)).hashCode())
                            displayEnterable(l,e,k);
                    }
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
        if(enterable.getPosition().equals(l.getPlayer().getPosition()))
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

    private static Level createLevelTest() throws IllegalLevelInsertionException {
        Particule startParticule = new Particule(Particule.State.CORPUSCULE, new Position(0,0));
        Conditionable endConditions = LevelCondition.createCondition(new Position(2,2), Particule.State.CORPUSCULE);
        Level level = new Level(3,4,startParticule,endConditions);
        BasicSquare b1 = new BasicSquare(new Position(0,0));
        level.addSquare(b1);
        BasicSquare b2 = new BasicSquare(new Position(1,0));
        level.addSquare(b2);
        BasicSquare b3 = new TPSquare(new Position(2,0),new Position(0,1));
        level.addSquare(b3);


        b1 = new BasicSquare(new Position(0,3));
        level.addSquare(b1);


        b1 = new BasicSquare(new Position(0,1));
        level.addSquare(b1);
        b2 = new BasicSquare(new Position(1,1));
        level.addSquare(b2);
        b3 = new HoleSquare(new Position(2,1));
        level.addSquare(b3);


        b1 = new BasicSquare(new Position(0,2));
        level.addSquare(b1);
        b2 = new BasicSquare(new Position(1,2));
        level.addSquare(b2);
        b3 = new ArrivalSquare(endConditions);
        level.addSquare(b3);

        List<Enterable> switcher1List = new ArrayList<>();
        BasicSquare b4 = new BasicSquare(new Position(3,0));
        switcher1List.add(b4);
        b4 = new HoleSquare(new Position(3,1));
        switcher1List.add(b4);
        b4 = new HoleSquare(new Position(3,2));
        switcher1List.add(b4);

        Switcher sw = new Switcher(switcher1List);
        level.addSwitcher(sw);


        return level;
    }


    public static void main (String []args){
        try {
            testLevelWin();
            testLevelLoose();
            testSwitcher();
        } catch (IllegalLevelInsertionException e) {
            e.printStackTrace();
        }

    }

    private static void testLevelWin() throws IllegalLevelInsertionException {
        Level l = createLevelTest();
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.DOWN);
        Playable.State state = l.play(Movable.Direction.RIGHT);
        assertLevelState(state, Playable.State.WIN);
    }
    private static void testLevelLoose() throws IllegalLevelInsertionException {
        System.out.println("testLevelLoose : ");
        Level l = createLevelTest();
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        Playable.State state = l.play(Movable.Direction.RIGHT);
        assertLevelState(state, Playable.State.LOOSE);
    }

    private static void assertLevelState(Playable.State got, Playable.State expected) {
        if (got != expected) {
            throw new RuntimeException("Level state should be "+expected+" but got : " + got);
        }else
            System.out.println("Ok");
    }

    private static void testSwitcher() throws IllegalLevelInsertionException {
        Level l = createLevelTest();
        displayLevel(l, Playable.State.CONTINUE);
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.DOWN));
        Playable.State state = l.play(Movable.Direction.RIGHT);
        displayLevel(l,state);
        assertLevelState(state, Playable.State.WIN);
    }
}