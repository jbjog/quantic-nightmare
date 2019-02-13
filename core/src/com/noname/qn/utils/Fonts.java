package com.noname.qn.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Fonts {
    private Fonts(){}

    public static BitmapFont getDefaultFont() {
        return getDefaultFont(20,Color.WHITE);
    }

    public static BitmapFont getDefaultFont(int size) {
        return getDefaultFont(size,Color.WHITE);
    }

    public static Label.LabelStyle getFocusStyle(){
        return new Label.LabelStyle(getDefaultFont(), Color.RED);
    }

    public static Label.LabelStyle getUnFocusStyle(){
        return new Label.LabelStyle(getDefaultFont(), Color.WHITE);
    }

    public static Label.LabelStyle getBigRedStyle(){
        return new Label.LabelStyle(getDefaultFont(40) ,Color.RED);

    }

    public static BitmapFont getDefaultFont(int size, Color c) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("theDarkFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = c;
        parameter.size = size;
        return generator.generateFont(parameter);

    }
}
