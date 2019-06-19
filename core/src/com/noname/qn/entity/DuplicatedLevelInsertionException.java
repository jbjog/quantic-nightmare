package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;
import com.noname.qn.utils.QNPreferences;
import com.noname.qn.utils.TextValues;

public class DuplicatedLevelInsertionException extends IllegalLevelInsertionException {
    public DuplicatedLevelInsertionException(Positionable p) {
        super(TextValues.DUPLICATED_LEVEL_INSERTION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage()]
                +"'"+p.getClass().getSimpleName()+"'"+
                TextValues.POSITION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage()],p);
    }
}
