package com.noname.qn.utils;

import com.noname.qn.entity.*;
import com.noname.qn.entity.square.*;
import com.noname.qn.service.domain.Conditionable;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelFactory {
    private LevelFactory() {}

    public static Levelable createLevel(Levelable.Levels levelNumber) throws IllegalLevelInsertionException, UnknownLevelException {
        switch (levelNumber){
            case TEST:
                return createLevelTest();
        }
        throw new UnknownLevelException(levelNumber);
    }
    private static Levelable createLevelTest() throws IllegalLevelInsertionException {
        //start at position 0,0 with Duality CORPUSCULE
        Particule startParticule = new Particule(Player.Duality.CORPUSCULE, new Position(0,0));
        //end if reach position 2,2 with Duality CORPUSCULE
        Conditionable endConditions = LevelCondition.createCondition(new Position(2,2), Player.Duality.CORPUSCULE);

        //create level with 3 rows 4 columns with above conditions
        Level level = new Level(3,4,startParticule,endConditions);

        //add line 1
        level.addSquare(new BasicSquare( 0,0));
        level.addSquare(new BasicSquare(1,0));
        level.addSquare(new TPSquare(2,0,0,1));

        //add line 2
        level.addSquare(new BasicSquare(0,1));
        level.addSquare(new BasicSquare(1,1));
        level.addSquare(new HoleSquare(2,1));

        //add line 3
        level.addSquare(new SwitchDualitySquare(0,2));
        level.addSquare(new BasicSquare(1,2));
        level.addSquare(new ArrivalSquare(endConditions));

        //add column 4 with a square switcher
        List<Enterable> switcher1List = new ArrayList<>();
        switcher1List.add(new BasicSquare(3,0));
        switcher1List.add(new HoleSquare(3,1));
        switcher1List.add(new HoleSquare(3,2));
        SquareSwitcher sw = new SquareSwitcher(switcher1List);
        level.addSwitcher(sw);


        return level;
    }
}
