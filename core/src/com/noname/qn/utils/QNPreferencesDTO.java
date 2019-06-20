package com.noname.qn.utils;

import com.noname.qn.service.domain.IQNPreferences;

public class QNPreferencesDTO implements IQNPreferences {
    private boolean enableEffects;
    private boolean enableMusic;
    private int language;

    public QNPreferencesDTO() {
        enableEffects = true;
        enableMusic = true;
        language = TextValues.ENGLISH;
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
    public int getLanguage() {
        return language;
    }
    @Override
    public void setLanguage(int language) {
        this.language = language;
    }
    @Override
    public void save() {}
}
