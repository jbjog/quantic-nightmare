package com.noname.qn;


import com.noname.qn.entity.*;
import com.noname.qn.entity.square.*;
import com.noname.qn.service.domain.*;
import com.noname.qn.utils.LevelBuilder;

public class QNGameTest {
    private static void displayLevel(Levelable l, Playable.State st){
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
    private static void displayEnterable(Levelable l, Enterable enterable, int lg) {
        String s = "-";
        if(enterable instanceof ArrivalSquare)
            s="*";
        if(enterable instanceof TPSquare)
            s="/";
        if(enterable instanceof HoleSquare)
            s="o";
        if(enterable instanceof SwitchDualitySquare)
            s="s";
        if(enterable.getPosition().equals(l.getPlayer().getPosition())){
            if(l.getPlayer().getDuality()== Player.Duality.CORPUSCULE)
                s="+";
            else
                s="~";
        }
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
            testSwitcher();
            testDualitySwitcherLoose();
        } catch (IllegalLevelInsertionException e) {

        }

    }

    private static void testLevelWin() throws IllegalLevelInsertionException {
        Levelable l = LevelBuilder.createLevelTest();
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.DOWN);
        Playable.State state = l.play(Movable.Direction.RIGHT);
        for(Turn t :l.getTracker()){
            System.out.println(t.toString());
            try{
                t.getTexture();
            }catch(Exception e){}
        }
        assertLevelState(state, Playable.State.WIN);
    }
    private static void testLevelLoose() throws IllegalLevelInsertionException {
        System.out.println("testLevelLoose : ");
        Levelable l = LevelBuilder.createLevelTest();
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        l.play(Movable.Direction.RIGHT);
        Playable.State state = l.play(Movable.Direction.RIGHT);
        for(Turn t :l.getTracker()){
            System.out.println(t.toString());
            try{
                t.getTexture();
            }catch(Exception e){}
        }
        assertLevelState(state, Playable.State.LOOSE);
    }

    private static void assertLevelState(Playable.State got, Playable.State expected) {
        if (got != expected) {
            throw new RuntimeException("Level state should be "+expected+" but got : " + got);
        }else
            System.out.println("Ok");
    }

    private static void testSwitcher() throws IllegalLevelInsertionException {
        Levelable l = LevelBuilder.createLevelTest();
        displayLevel(l, Playable.State.CONTINUE);
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        displayLevel(l,l.play(Movable.Direction.DOWN));
        Playable.State state = l.play(Movable.Direction.RIGHT);
        displayLevel(l,state);
        assertLevelState(state, Playable.State.WIN);
    }
    private static void testDualitySwitcherLoose() throws IllegalLevelInsertionException {
        Levelable l = LevelBuilder.createLevelTest();
        displayLevel(l, Playable.State.CONTINUE);
        displayLevel(l,l.play(Movable.Direction.DOWN));
        displayLevel(l,l.play(Movable.Direction.DOWN));
        displayLevel(l,l.play(Movable.Direction.RIGHT));
        Playable.State state = l.play(Movable.Direction.RIGHT);
        displayLevel(l,state);
        assertLevelState(state, Playable.State.LOOSE);
    }
}