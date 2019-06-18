package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


public class FileHandling {

    public PlayerScore playerScore;

    public void writeScore (int lvl, int score) {
        // où ecrire
        FileHandle file = Gdx.files.local("save/score" + lvl+".json");
        playerScore = new PlayerScore();
        Json json = new Json();

        playerScore.setLvl(lvl);
        playerScore.setScore(score);

        String playerScores = json.toJson(playerScore);
        file.writeString(playerScores,false);
    }

    public PlayerScore readScore(int numLvl) {
        // où lire
        FileHandle file = Gdx.files.local("save/score" + numLvl +".json");
        String scores = file.readString();
        Json json = new Json();

        PlayerScore playerSave = json.fromJson(PlayerScore.class, scores);

        return playerSave;
    }
}

// exemple
//fileHandling = new FileHandling();
//fileHandling.writeScore(9,30);
//fileHandling.readScore(9);