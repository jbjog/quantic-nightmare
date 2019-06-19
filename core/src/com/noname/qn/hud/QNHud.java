package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.utils.FileHandling;
import com.noname.qn.utils.Fonts;

public class QNHud implements Disposable{
    public Gamer screen;
    protected Viewport viewport;
    protected BitmapFont font;
    public Stage stage;
    public static boolean enableMusic = true;
    public static boolean enableEffects = true;
    public FileHandling fileHandling;

    public QNHud(Gamer screen) {
        this.screen = screen;
        viewport = new FitViewport(800, 480, new OrthographicCamera());
        stage = new Stage(viewport, screen.getGamable().getBatch());
        Gdx.input.setInputProcessor(stage);

        font = Fonts.getDefaultFont();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
