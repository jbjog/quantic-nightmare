package com.noname.qn.service.domain;

import com.noname.qn.parameters.TextValues;
import com.noname.qn.service.gui.Gamable;

public interface IQNPreferences {
    boolean isEnableEffects();

    void setEnableEffects(boolean enableEffects);

    boolean isEnableMusic();

    void setEnableMusic(boolean enableMusic);

    TextValues.Language getLanguage();

    void setLanguage(TextValues.Language language);

    Gamable.Difficulty getDifficulty();

    void setDifficulty(Gamable.Difficulty difficulty);

    void save();
}
