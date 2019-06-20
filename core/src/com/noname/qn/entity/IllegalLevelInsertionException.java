package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;
import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.parameters.TextValues;

public class IllegalLevelInsertionException extends Exception {
    public IllegalLevelInsertionException(String message,Positionable p) {
        super(message+" : ("+p.getPosition().getX()+","+p.getPosition().getY()+")");
    }
    public IllegalLevelInsertionException(Positionable p) {
        this(TextValues.ILLEGAL_LEVEL_INSERTION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage().ordinal()],p);
    }
}
