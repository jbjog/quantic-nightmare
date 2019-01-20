package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.noname.qn.service.gui.Focusable;

public class FocusableImageButton extends ImageButton implements Focusable {
    Drawable focusDrawable;
    Drawable unFocusDrawable;
    public FocusableImageButton(String focusFilePath,String unFocusFilePath) {
        super(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(unFocusFilePath)))));
        this.focusDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(focusFilePath))));
        this.unFocusDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(unFocusFilePath))));
    }

    @Override
    public void setFocus(boolean focus) {
        ImageButtonStyle oldStyle = getStyle();
        if(focus)
            oldStyle.imageUp = focusDrawable;
        else
            oldStyle.imageUp =  unFocusDrawable;
        setStyle(oldStyle);
    }
}
