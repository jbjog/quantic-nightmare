package com.noname.qn.entity;

import com.noname.qn.service.Positionable;

public class OutOfBoundsLevelInsertionException extends IllegalLevelInsertionException {
    public OutOfBoundsLevelInsertionException(Positionable p,int nbRows,int nbColumns) {
        super("OutOfBounds Insertion in Level[lg:"+nbRows+",cols:"+nbColumns+"], '"+p.getClass().getSimpleName()+"' at position",p);
    }
}
