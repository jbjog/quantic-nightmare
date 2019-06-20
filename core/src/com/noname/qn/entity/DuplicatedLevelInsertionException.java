package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;
import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.parameters.TextValues;

public class DuplicatedLevelInsertionException extends IllegalLevelInsertionException {
    public DuplicatedLevelInsertionException(Positionable p) {
        super(TextValues.DUPLICATED_LEVEL_INSERTION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage().ordinal()]
                +"'"+p.getClass().getSimpleName()+"'"+
                TextValues.POSITION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage().ordinal()],p);
    }
}
