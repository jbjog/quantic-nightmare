package com.noname.qn.entity;

import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.parameters.TextValues;

public class UnknownLevelException extends Exception {
    private UnknownLevelException(String message, int levelNumber) {
        super(message+" : "+levelNumber);
    }
    public UnknownLevelException(int levelNumber) {
        this(TextValues.UNKNOWN_LEVEL_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage().ordinal()],levelNumber);
    }
}
