package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Fonts {
    private Fonts(){}

    public static BitmapFont getDefaultFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("theDarkFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 20;
        return generator.generateFont(parameter);
    }
    public static Label.LabelStyle getFocusStyle(){
        return new Label.LabelStyle(getDefaultFont(), Color.RED);
    }

    public static Label.LabelStyle getUnFocusStyle(){
        return new Label.LabelStyle(getDefaultFont(), Color.WHITE);

    }
}
