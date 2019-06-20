package com.noname.qn.parameters;

import com.noname.qn.service.domain.IQNPreferences;
import com.noname.qn.service.gui.Gamable;
import com.noname.qn.utils.FileHandling;

public final class QNPreferences implements IQNPreferences {

    public static volatile QNPreferences instance = null;

    private QNPreferences(){
        enableEffects = true;
        enableMusic = true;
        language = TextValues.Language.ENGLISH;
        difficulty = Gamable.Difficulty.EASY;
    }


    public static QNPreferences getPref() {
        if (instance == null) {
            synchronized(QNPreferences.class) {
                if (instance == null) {
                    instance = new QNPreferences();
                    instance.loadDataFromFile();
                }
            }
        }
        return instance;
    }

    private void loadDataFromFile() {
    }


    private boolean enableEffects;
    private boolean enableMusic;
    private TextValues.Language language;
    private Gamable.Difficulty difficulty;

    @Override
    public boolean isEnableEffects() {
        return enableEffects;
    }
    @Override
    public void setEnableEffects(boolean enableEffects) {
        this.enableEffects = enableEffects;
    }
    @Override
    public boolean isEnableMusic() {
        return enableMusic;
    }
    @Override
    public void setEnableMusic(boolean enableMusic) {
        this.enableMusic = enableMusic;
    }
    @Override
    public TextValues.Language getLanguage() {
        return language;
    }
    @Override
    public void setLanguage(TextValues.Language language) {
        this.language = language;
    }
    @Override
    public Gamable.Difficulty getDifficulty() {
        return difficulty;
    }
    @Override
    public void setDifficulty(Gamable.Difficulty difficulty) {
        this.difficulty=difficulty;
    }
    @Override
    public void save() {
        FileHandling.writePreferences(this);
    }
}
