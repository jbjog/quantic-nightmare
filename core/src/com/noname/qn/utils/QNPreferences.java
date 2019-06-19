package com.noname.qn.utils;

public final class QNPreferences {

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

    public boolean isEnableEffects() {
        return enableEffects;
    }
    public void setEnableEffects(boolean enableEffects) {
        this.enableEffects = enableEffects;
    }
    public boolean isEnableMusic() {
        return enableMusic;
    }
    public void setEnableMusic(boolean enableMusic) {
        this.enableMusic = enableMusic;
    }
    public int getLanguage() {
        return language;
    }
    public void setLanguage(int language) {
        this.language = language;
    }
}
