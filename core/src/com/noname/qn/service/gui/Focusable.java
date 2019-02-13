package com.noname.qn.service.gui;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public interface Focusable {
    void setFocus(boolean focus);

    ClickListener getAction ();
}
