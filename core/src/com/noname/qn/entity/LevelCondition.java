package com.noname.qn.entity;

import com.noname.qn.service.Positionable;
import com.noname.qn.service.Conditionable;
import com.noname.qn.service.Player;

public class LevelCondition {
    public static Conditionable createCondition(Position p , Player.State s ){
        return new Conditionable() {
            @Override
            public Position getPosition() {
                return p;
            }

            @Override
            public Player.State getState() {
                return s;
            }
        };
    }
}
