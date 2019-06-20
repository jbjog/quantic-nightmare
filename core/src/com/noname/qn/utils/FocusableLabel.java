package com.noname.qn.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Focusable;

public class FocusableLabel extends Label implements Focusable {
    private LabelStyle focusStyle;
    private LabelStyle unFocusStyle;
    private ClickListener event;

    public FocusableLabel(CharSequence text, ClickListener event) {
        this(text,Fonts.getFocusStyle(),Fonts.getUnFocusStyle(),event);
    }

    public FocusableLabel(CharSequence text, LabelStyle focusStyle, LabelStyle unFocusStyle, ClickListener event) {
        super(text, unFocusStyle);
        this.focusStyle = focusStyle;
        this.unFocusStyle = unFocusStyle;
        this.event = event;
    }

    @Override
    public void setFocus(boolean focus) {
        if(focus)
            setStyle(focusStyle);
        else
            setStyle(unFocusStyle);
    }

    @Override
    public ClickListener getAction() {
        return event;
    }
}
