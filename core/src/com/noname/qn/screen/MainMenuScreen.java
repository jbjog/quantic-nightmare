package com.noname.qn.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.noname.qn.QNGame;

public class MainMenuScreen implements Screen {
    private final QNGame game;

    private OrthographicCamera camera;

    public MainMenuScreen(final QNGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(2, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Quantic Nightmare", 100, 450);
		game.font.draw(game.batch, "New Game", 350, 300);
		game.font.draw(game.batch, "Continue", 350, 250);
		game.font.draw(game.batch, "Options", 350, 200);
		game.batch.end();

		if (Gdx.input.isTouched()) {
		    game.setScreen(new StageMenuScreen(game));
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
