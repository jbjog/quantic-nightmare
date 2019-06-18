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
        public static final Result NONE = new Result(0);
        public static final Result SUCCEED = new Result(0.1);
        public static final Result BRONZE = new Result(0.5);
        public static final Result SILVER = new Result(0.8);
        public static final Result GOLD = new Result(1);

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
            if (percent == o.percent)
                return 0;
            else if (percent < o.percent)
                return 1;
            else
                return -1;
        }
    }
}
