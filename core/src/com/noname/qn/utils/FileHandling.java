package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


public class FileHandling {

    public static void writeScore (int lvl, int  minMoves, int score) {
        // où ecrire
        FileHandle file = Gdx.files.local("save/score" + lvl+".json");
        PlayerScore playerScore = new PlayerScore();
        Json json = new Json();

        playerScore.setLvl(lvl);
        playerScore.setMinMove(minMoves);
        playerScore.setScore(score);

        String playerScores = json.toJson(playerScore);
        file.writeString(playerScores,false);
    }

    public static PlayerScore readScore(int numLvl) {
        // où lire
        FileHandle file = Gdx.files.local("save/score" + numLvl +".json");
        String scores = file.readString();
        Json json = new Json();

        return json.fromJson(PlayerScore.class, scores);
    }
}

// exemple
//fileHandling = new FileHandling();
////fileHandling.writeScore(9,30);
////fileHandling.readScore(9);