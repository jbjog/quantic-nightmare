package com.noname.qn.utils;

import com.noname.qn.service.domain.IQNPreferences;

public final class QNPreferences implements IQNPreferences {

    public static volatile QNPreferences instance = null;

    private QNPreferences(){}


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


    private boolean enableEffects = true;
    private boolean enableMusic = true;
    private int language = TextValues.ENGLISH;

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
    public void save() {
        FileHandling.writePreferences(this);
    }
}
