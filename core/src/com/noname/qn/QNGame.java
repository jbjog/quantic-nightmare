package com.noname.qn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QNGame extends Game {
	public SpriteBatch batch;
	private Texture img;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("QN.png");
	}

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img,0,0);
//		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
/*
	private Level createLevelTest(){
		Level level = new Level(3,3,new Position(0,0),new Position(2,2), Particule.State.CORPUSCULE)
	}*/
}
