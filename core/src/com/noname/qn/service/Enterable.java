package com.noname.qn.service;

import com.noname.qn.entity.Level;
import com.noname.qn.entity.Particule;

public interface Enterable extends Hideable,Positionable {
    Level.State enter(Particule particule);
}
