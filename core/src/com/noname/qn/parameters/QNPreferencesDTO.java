package com.noname.qn.parameters;

import com.noname.qn.service.domain.IQNPreferences;
import com.noname.qn.service.gui.Gamable;

public class QNPreferencesDTO implements IQNPreferences {
    private boolean enableEffects;
    private boolean enableMusic;
    private TextValues.Language language;
    private Gamable.Difficulty difficulty;

    public QNPreferencesDTO() {
        enableEffects = true;
        enableMusic = true;
        language = TextValues.Language.ENGLISH;
        difficulty = Gamable.Difficulty.EASY;
    }

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
    public void save() {}
}
