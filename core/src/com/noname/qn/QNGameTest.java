package com.noname.qn;


import com.noname.qn.entity.*;
import com.noname.qn.entity.square.ArrivalSquare;
import com.noname.qn.entity.square.BasicSquare;
import com.noname.qn.entity.square.HoleSquare;
import com.noname.qn.entity.square.TPSquare;
import com.noname.qn.service.domain.Conditionable;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.domain.Playable;

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
        //start at position 0,0 with State CORPUSCULE
        Particule startParticule = new Particule(Particule.State.CORPUSCULE, new Position(0,0));
        //end if reach position 2,2 with State CORPUSCULE
        Conditionable endConditions = LevelCondition.createCondition(new Position(2,2), Particule.State.CORPUSCULE);

        //create level with 3 rows 4 columns with above conditions
        Level level = new Level(3,4,startParticule,endConditions);

        //add line 1
        level.addSquare(new BasicSquare( 0,0));

        //force error : duplicated entry
        try{
            level.addSquare(new BasicSquare( 0,0));
        }catch (IllegalLevelInsertionException e){
            System.out.println(e.getMessage());
        }
        //force error : Out of Bounds entry
        try{
            level.addSquare(new BasicSquare( 0,3));
        }catch (IllegalLevelInsertionException e){
            System.out.println(e.getMessage());
        }

        level.addSquare(new BasicSquare(1,0));
        level.addSquare(new TPSquare(2,0,0,1));

        //add line 2
        level.addSquare(new BasicSquare(0,1));
        level.addSquare(new BasicSquare(1,1));
        level.addSquare(new HoleSquare(2,1));

        //add line 3
        level.addSquare(new BasicSquare(0,2));
        level.addSquare(new BasicSquare(1,2));
        level.addSquare(new ArrivalSquare(endConditions));

        //add column 4 with a square switcher
        List<Enterable> switcher1List = new ArrayList<>();
        switcher1List.add(new BasicSquare(3,0));
        switcher1List.add(new HoleSquare(3,1));
        switcher1List.add(new HoleSquare(3,2));
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