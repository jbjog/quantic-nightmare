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

    public static Levelable createLevel(int levelNumber) throws IllegalLevelInsertionException, UnknownLevelException {
        switch (levelNumber){
            case 1:
                return createLevel1();
            case 2:
                return createLevel2();
            case 4:
                return createLevel4();
        }
        throw new UnknownLevelException(levelNumber);
    }

    private static Levelable createLevel2() throws IllegalLevelInsertionException {
        //start at position 0,0 with Duality CORPUSCULE
        Particule startParticule = new Particule(Player.Duality.CORPUSCULE, new Position(1,0));
        //end if reach position 2,2 with Duality WAVE
        Conditionable endConditions = LevelCondition.createCondition(new Position(4,4), Player.Duality.WAVE);

        //create level with 3 rows 4 columns with above conditions
        Level level = new Level(5,5,startParticule,endConditions);

        //add line 1
        level.addSquare(new HoleSquare(0,0));
        level.addSquare(new BasicSquare(1,0));
        level.addSquare(new HoleSquare(2,0));
        Enterable arrivalTP1 = new BasicSquare(3,0);
        level.addSquare(arrivalTP1);
        level.addSquare(new BasicSquare(4,0));

        //add line 2
        //level.addSquare(new BasicSquare(0,1));
        level.addSquare(new BasicSquare(1,1));
        level.addSquare(new HoleSquare(2,1));
        level.addSquare(new HoleSquare(3,1));
        level.addSquare(new BasicSquare(4,1));

        //add line 3
        Enterable arrivalTP2 = new BasicSquare(4,3);
        level.addSquare(arrivalTP2);
        level.addSquare(new TPSquare(0,2,arrivalTP2));
        level.addSquare(new TPSquare(1,2,arrivalTP1));
        level.addSquare(new BasicSquare(2,2));
        level.addSquare(new BasicSquare(3,2));
        level.addSquare(new BasicSquare(4,2));

        //add line 4
        level.addSquare(new BasicSquare(0,3));
        level.addSquare(new HoleSquare(1,3));
        level.addSquare(new SwitchDualitySquare(2,3));
        level.addSquare(new HoleSquare(3,3));


        //add line 5
        level.addSquare(new BasicSquare(0,4));
        level.addSquare(new BasicSquare(1,4));
        level.addSquare(new BasicSquare(2,4));
        level.addSquare(new HoleSquare(3,4));
        level.addSquare(new ArrivalSquare(endConditions));


        return level;

    }
    private static Levelable createLevel1() throws IllegalLevelInsertionException {
        //start at position 0,0 with Duality CORPUSCULE
        Particule startParticule = new Particule(Player.Duality.CORPUSCULE, new Position(0,0));
        //end if reach position 2,2 with Duality CORPUSCULE
        Conditionable endConditions = LevelCondition.createCondition(new Position(2,2), Player.Duality.CORPUSCULE);

        //create level with 3 rows 4 columns with above conditions
        Level level = new Level(3,4,startParticule,endConditions);

        //add line 1
        level.addSquare(new BasicSquare( 0,0));
        level.addSquare(new BasicSquare(1,0));

        //add line 2
        Enterable arrivalTP = new BasicSquare(0,1);
        level.addSquare(arrivalTP);
        level.addSquare(new BasicSquare(1,1));
        level.addSquare(new HoleSquare(2,1));

        //tp line 1
        level.addSquare(new TPSquare(2,0,arrivalTP));

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

    private static Levelable createLevel4() throws IllegalLevelInsertionException {
        //start at position 0,0 with Duality CORPUSCULE
        Particule startParticule = new Particule(Player.Duality.CORPUSCULE, new Position(1, 0));
        //end if reach position 2,2 with Duality WAVE
        Conditionable endConditions = LevelCondition.createCondition(new Position(4, 4), Player.Duality.CORPUSCULE);

        //create level with 3 rows 4 columns with above conditions
        Level level = new Level(5, 17, startParticule, endConditions);

        //add line 1
        level.addSquare(new BasicSquare( 0,0));
        level.addSquare(new BasicSquare( 1,0));
        level.addSquare(new BasicSquare( 2,0));
        //level.addSquare(new BasicSquare( 3,0));
        //level.addSquare(new BasicSquare( 4,0));
        //level.addSquare(new BasicSquare( 5,0));
        level.addSquare(new BasicSquare( 6,0));
        level.addSquare(new BasicSquare( 7,0));
        //level.addSquare(new BasicSquare( 8,0));
        level.addSquare(new BasicSquare( 9,0));
        level.addSquare(new BasicSquare( 10,0));
        //level.addSquare(new BasicSquare( 11,0));
        level.addSquare(new BasicSquare( 12,0));
        level.addSquare(new BasicSquare( 13,0));
        level.addSquare(new BasicSquare( 14,0));
        level.addSquare(new BasicSquare( 15,0));
        //level.addSquare(new BasicSquare( 16,0));

        //add line 2
        level.addSquare(new BasicSquare( 0,1));
        //level.addSquare(new BasicSquare( 1,1));
        //level.addSquare(new BasicSquare( 2,1));
        level.addSquare(new BasicSquare( 3,1));
        level.addSquare(new BasicSquare( 4,1));
        level.addSquare(new BasicSquare( 5,1));
        level.addSquare(new BasicSquare( 6,1));
        //level.addSquare(new BasicSquare( 7,1));
        level.addSquare(new BasicSquare( 8,1));
        //level.addSquare(new BasicSquare( 9,1));
        level.addSquare(new BasicSquare( 10,1));
        level.addSquare(new BasicSquare( 11,1));
        //level.addSquare(new BasicSquare( 12,1));
        //level.addSquare(new BasicSquare( 13,1));
        //level.addSquare(new BasicSquare( 14,1));
        //level.addSquare(new BasicSquare( 15,1));
        level.addSquare(new BasicSquare( 16,1));

        //add line 3
        level.addSquare(new BasicSquare( 0,2));
        level.addSquare(new BasicSquare( 1,2));
        level.addSquare(new BasicSquare( 2,2));
        //level.addSquare(new BasicSquare( 3,2));
        //level.addSquare(new BasicSquare( 4,2));
        //level.addSquare(new BasicSquare( 5,2));
        level.addSquare(new BasicSquare( 6,2));
        //level.addSquare(new BasicSquare( 7,2));
        //level.addSquare(new BasicSquare( 8,2));
        //level.addSquare(new BasicSquare( 9,2));
        level.addSquare(new BasicSquare( 10,2));
        level.addSquare(new BasicSquare( 11,2));
        //level.addSquare(new BasicSquare( 12,2));
        //level.addSquare(new BasicSquare( 13,2));
        //level.addSquare(new BasicSquare( 14,2));
        level.addSquare(new BasicSquare( 15,2));
        //level.addSquare(new BasicSquare( 16,2));

        //add line 4
        level.addSquare(new BasicSquare( 0,3));
        //level.addSquare(new BasicSquare( 1,3));
        //level.addSquare(new BasicSquare( 2,3));
        level.addSquare(new BasicSquare( 3,3));
        level.addSquare(new BasicSquare( 4,3));
        level.addSquare(new BasicSquare( 5,3));
        level.addSquare(new BasicSquare( 6,3));
        //level.addSquare(new BasicSquare( 7,3));
        //level.addSquare(new BasicSquare( 8,3));
        //level.addSquare(new BasicSquare( 9,3));
        level.addSquare(new BasicSquare( 10,3));
        //level.addSquare(new BasicSquare( 11,3));
        level.addSquare(new BasicSquare( 12,3));
        //level.addSquare(new BasicSquare( 13,3));
        level.addSquare(new BasicSquare( 14,3));
        //level.addSquare(new BasicSquare( 15,3));
        //level.addSquare(new BasicSquare( 16,3));

        //add line 5
        level.addSquare(new BasicSquare( 0,4));
        level.addSquare(new BasicSquare( 1,4));
        level.addSquare(new BasicSquare( 2,4));
        //level.addSquare(new BasicSquare( 3,4));
        //level.addSquare(new BasicSquare( 4,4));
        //level.addSquare(new BasicSquare( 5,4));
        level.addSquare(new BasicSquare( 6,4));
        //level.addSquare(new BasicSquare( 7,4));
        //level.addSquare(new BasicSquare( 8,4));
        //level.addSquare(new BasicSquare( 9,4));
        level.addSquare(new BasicSquare( 10,4));
        //level.addSquare(new BasicSquare( 11,4));
        //level.addSquare(new BasicSquare( 12,4));
        level.addSquare(new BasicSquare( 13,4));
        level.addSquare(new BasicSquare( 14,4));
        level.addSquare(new BasicSquare( 15,4));
        level.addSquare(new BasicSquare( 16,4));



        return level;

    }
}
