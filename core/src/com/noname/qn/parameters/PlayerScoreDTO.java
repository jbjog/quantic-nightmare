package com.noname.qn.parameters;

public class PlayerScoreDTO {
    private int lvl;
    private int score;
    private int minMove;

    public int getMinMove() {
        return minMove;
    }

    public void setMinMove(int minMove) {
        this.minMove = minMove;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
