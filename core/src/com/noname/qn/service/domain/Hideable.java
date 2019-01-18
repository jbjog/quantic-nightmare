package com.noname.qn.service.domain;

public interface Hideable extends Texturable{
    boolean isHidden();
    void unhide();
}
