package com.noname.qn.service.gui;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public interface Focusable {
    void setFocus(boolean focus);

    DelayedRemovalArray<EventListener> getListeners ();
}
