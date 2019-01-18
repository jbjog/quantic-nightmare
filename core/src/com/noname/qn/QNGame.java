package com.noname.qn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.qn.screen.MainMenuScreen;

public class QNGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
/*
	private Level createLevelTest(){
		Level level = new Level(3,3,new Position(0,0),new Position(2,2), Particule.State.CORPUSCULE)
	}*/
}
