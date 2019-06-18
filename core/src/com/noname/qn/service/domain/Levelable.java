package com.noname.qn.service.domain;

import com.noname.qn.entity.IllegalLevelInsertionException;

import java.util.List;

public interface Levelable extends Tracker,Playable {
    int getNbRows();
    int getNbColumns();
    Player getPlayer();
    void addSwitcher(Switcher s)throws IllegalLevelInsertionException;
    List<Enterable> getSquares();
    void addSquare(Enterable square) throws IllegalLevelInsertionException;

    class Result implements Comparable<Result>{
        public static final Result NONE = new Result(10){
            @Override
            public String toString() {
                return "NONE";
            }
        };
        public static final Result SUCCEED = new Result(20){
            @Override
            public String toString() {
                return "SUCCEED";
            }
        };
        public static final Result BRONZE = new Result(30){
            @Override
            public String toString() {
                return "BRONZE";
            }
        };
        public static final Result SILVER = new Result(40){
            @Override
            public String toString() {
                return "SILVER";
            }
        };
        public static final Result GOLD = new Result(50){
            @Override
            public String toString() {
                return "GOLD";
            }
        };

        private int value;
        public Result(int value){
            this.value=value;
        }
        @Override
        public int compareTo(Result o) {
            if(value==o.value)
                return 0;
            else if(value<o.value)
                return 1;
            else
                return -1;
        }
        public static Result getFromString(String resultToString) throws InvalidLevelResultString {
            switch (resultToString){
                case "NONE":
                    return NONE;
                case "SUCCEED":
                    return SUCCEED;
                case "BRONZE":
                    return BRONZE;
                case "SILVER":
                    return SILVER;
                case "GOLD":
                    return GOLD;
            }
            throw new InvalidLevelResultString(resultToString);
        }

        private static class InvalidLevelResultString extends Exception {
            public InvalidLevelResultString(String resultToString) {
                super("Invalid Level Result : "+resultToString);
            }
        }
    }
}
