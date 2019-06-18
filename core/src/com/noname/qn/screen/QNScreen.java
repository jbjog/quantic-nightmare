package com.noname.qn.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.qn.hud.QNHud;
import com.noname.qn.service.gui.Gamable;
import com.noname.qn.service.gui.Gamer;

public class QNScreen implements Screen, Gamer {
    private final Gamable game;
    protected OrthographicCamera camera;
    protected QNHud hud;
    private SpriteBatch batch;
    private Sprite splash;


    public QNScreen(final Gamable game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();

        camera.update();

        game.getBatch().begin();
        game.getBatch().end();

        game.getBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashtexture = new Texture("no_name_studio.jpg");
        splash = new Sprite(splashtexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

    @Override
    public Gamable getGamable() {
        return game;
    }

}
