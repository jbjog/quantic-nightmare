package com.noname.qn.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.noname.qn.QNGame;

public class MainMenuScreen implements Screen {
    private final QNGame game;
    private OrthographicCamera camera;
    private StageMenuScreen screen;


    public MainMenuScreen(final QNGame game) {
        this.game = game;
        screen = new StageMenuScreen(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Quantic Nightmare", 270, 400);
		game.font.draw(game.batch, "Play", 375, 300);
		game.font.draw(game.batch, "Options", 375, 250);
		game.font.draw(game.batch, "Quit", 375, 200);
		game.batch.end();

		if (Gdx.input.justTouched()) {
            game.setScreen(screen);
		    dispose();
        }
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

    }
}
