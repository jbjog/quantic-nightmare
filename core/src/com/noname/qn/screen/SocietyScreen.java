package com.noname.qn.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.qn.service.gui.Gamable;

public class SocietyScreen implements Screen {

    private SpriteBatch batch;
    private Sprite splash;
    private final Gamable game;

    public SocietyScreen(final Gamable game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashtexture = new Texture("QNNameLogo.jpg");
        splash = new Sprite(splashtexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        SplashScreen.Sprite(batch, splash);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        splash.getTexture().dispose();

    }
}
