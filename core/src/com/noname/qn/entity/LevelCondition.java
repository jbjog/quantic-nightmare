package com.noname.qn.entity;

import com.noname.qn.service.domain.Conditionable;
import com.noname.qn.service.domain.Player;

public class LevelCondition {
    public static Conditionable createCondition(Position p , Player.Duality s ){
        return new Conditionable() {
            @Override
            public Position getPosition() {
                return p;
            }

            @Override
            public Player.Duality getDuality() {
                return s;
            }
        };
    }
}
