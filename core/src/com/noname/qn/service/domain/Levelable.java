package com.noname.qn.service.domain;

import com.noname.qn.entity.IllegalLevelInsertionException;

import java.util.List;

public interface Levelable extends Tracker,Playable {
    int getLevelNumber();
    int getNbRows();
    int getNbColumns();
    Player getPlayer();
    void addSwitcher(Switcher s)throws IllegalLevelInsertionException;
    List<Enterable> getSquares();
    void addSquare(Enterable square) throws IllegalLevelInsertionException;
    int getMinimumMoves();
    void setMinimumMoves(int min);
    String getName();
    void setName(String name);
    int getBestResult();
    void setBestResult(int r);

    class Result implements Comparable<Result>{
        public static final Result NONE = new Result(0){
            @Override
            public String toString() {
                return "NONE";
            }
        };
        public static final Result SUCCEED = new Result(0.1){
            @Override
            public String toString() {
                return "SUCCEED";
            }
        };
        public static final Result BRONZE = new Result(0.5){
            @Override
            public String toString() {
                return "BRONZE";
            }
        };
        public static final Result SILVER = new Result(0.8){
            @Override
            public String toString() {
                return "SILVER";
            }
        };
        public static final Result GOLD = new Result(1){
            @Override
            public String toString() {
                return "GOLD";
            }
        };

        private double percent;
        private Result(double percent){
            this.percent=percent;
        }
        public static Result getResultFromPercent(double percent){
            if(percent==1)
                return GOLD;
            else if(percent>=0.8)
                return SILVER;
            else if(percent>=0.5)
                return BRONZE;
            else if(percent>0)
                return SUCCEED;
            else
                return NONE;
        }
        @Override
        public int compareTo(Result o) {
            if(percent==o.percent)
                return 0;
            else if(percent<o.percent)
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
