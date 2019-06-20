package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;
import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.parameters.TextValues;

public class OutOfBoundsLevelInsertionException extends IllegalLevelInsertionException {
    public OutOfBoundsLevelInsertionException(Positionable p,int nbRows,int nbColumns) {
        super(TextValues.OUT_OF_BOUNDS_LEVEL_INSERTION_EXCEPTION_MESSAGE1[QNPreferences.getPref().getLanguage().ordinal()]+
                "[lg:"+nbRows+",cols:"+nbColumns+"], '"+p.getClass().getSimpleName()+"'"
                +TextValues.POSITION_EXCEPTION_MESSAGE[QNPreferences.getPref().getLanguage().ordinal()],p);
    }
}
