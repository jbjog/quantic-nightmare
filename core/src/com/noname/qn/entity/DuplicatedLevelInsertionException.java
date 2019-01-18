package com.noname.qn.entity;

import com.noname.qn.service.domain.Positionable;

public class DuplicatedLevelInsertionException extends IllegalLevelInsertionException {
    public DuplicatedLevelInsertionException(Positionable p) {
        super("Duplicated Level Insertion '"+p.getClass().getSimpleName()+"' at position",p);
    }
}
