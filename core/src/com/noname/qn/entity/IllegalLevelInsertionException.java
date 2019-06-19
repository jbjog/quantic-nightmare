package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;
import com.noname.qn.utils.QNPreferences;
import com.noname.qn.utils.TextValues;

public class IllegalLevelInsertionException extends Exception {
    public IllegalLevelInsertionException(String message,Positionable p) {
        super(message+" : ("+p.getPosition().getX()+","+p.getPosition().getY()+")");
    }
    public IllegalLevelInsertionException(Positionable p) {
        this(TextValues.ILLEGAL_LEVEL_INSERTION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage()],p);
    }
}
