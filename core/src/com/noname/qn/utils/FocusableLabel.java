package com.noname.qn.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.noname.qn.service.gui.Focusable;

public class FocusableLabel extends Label implements Focusable {
    private LabelStyle focusStyle;
    private LabelStyle unFocusStyle;
    public FocusableLabel(CharSequence text) {
        this(text,Fonts.getFocusStyle(),Fonts.getUnFocusStyle());
    }

    public FocusableLabel(CharSequence text, LabelStyle focusStyle, LabelStyle unFocusStyle) {
        super(text, unFocusStyle);
        this.focusStyle = focusStyle;
        this.unFocusStyle = unFocusStyle;
    }

    @Override
    public void setFocus(boolean focus) {
        if(focus)
            setStyle(focusStyle);
        else
            setStyle(unFocusStyle);
    }
}
