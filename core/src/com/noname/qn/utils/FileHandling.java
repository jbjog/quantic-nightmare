package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.SerializationException;
import com.noname.qn.parameters.PlayerScoreDTO;
import com.noname.qn.parameters.QNPreferencesDTO;
import com.noname.qn.service.domain.IQNPreferences;


public class FileHandling {

    public static void writeScore (int lvl, int  minMoves, int score) {
        // où ecrire
        FileHandle file = Gdx.files.local("save/score" + lvl+".json");
        PlayerScoreDTO playerScoreDTO = new PlayerScoreDTO();
        Json json = new Json();

        playerScoreDTO.setLvl(lvl);
        playerScoreDTO.setMinMove(minMoves);
        playerScoreDTO.setScore(score);

        String playerScores = json.toJson(playerScoreDTO);
        System.out.println(playerScores);
        file.writeString(playerScores,false);
    }

    public static PlayerScoreDTO readScore(int numLvl) {
        // où lire
        FileHandle file = Gdx.files.local("save/score" + numLvl +".json");
        String scores = file.readString();
        Json json = new Json();

        return json.fromJson(PlayerScoreDTO.class, scores);
    }

    public static void writePreferences (IQNPreferences pref) {
        // où ecrire
        FileHandle file = Gdx.files.local("preferences/pref.json");

        QNPreferencesDTO prefDTO = new QNPreferencesDTO();
        prefDTO.setLanguage(pref.getLanguage());
        prefDTO.setEnableEffects(pref.isEnableEffects());
        prefDTO.setEnableMusic(pref.isEnableMusic());
        prefDTO.setDifficulty(pref.getDifficulty());

        Json json = new Json();
        json.setTypeName(null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(JsonWriter.OutputType.json);
        String preferences = json.toJson(prefDTO);

        file.writeString(preferences,false);
        System.out.println("ecrit!");
    }

    public static IQNPreferences readPreferences() {
        // où lire
        FileHandle file = Gdx.files.local("preferences/pref.json");
        if (!file.exists()){
            return new QNPreferencesDTO();
        }
        String prefs = file.readString();
        Json json = new Json();
        try {
            return json.fromJson(QNPreferencesDTO.class, prefs);
        }catch (SerializationException e){
            e.printStackTrace();
            return new QNPreferencesDTO();
        }
    }

}

// exemple
//fileHandling = new FileHandling();
////fileHandling.writeScore(9,30);
////fileHandling.readScore(9);