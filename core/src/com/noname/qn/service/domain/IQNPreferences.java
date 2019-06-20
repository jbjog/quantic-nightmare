package com.noname.qn.service.domain;

public interface IQNPreferences {
    boolean isEnableEffects();

    void setEnableEffects(boolean enableEffects);

    boolean isEnableMusic();

    void setEnableMusic(boolean enableMusic);

    int getLanguage();

    void setLanguage(int language);

    void save();
}
