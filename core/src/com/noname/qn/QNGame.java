package com.noname.qn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.qn.screen.*;
import com.noname.qn.service.gui.Gamable;

public class QNGame extends Game implements Gamable {
	private SpriteBatch batch;
	private MainMenuScreen mms;
	private StageMenuScreen sms;
	private OptionMenuScreen oms;

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

	@Override
	public void changeScreen(Type screen){
		switch(screen){
			case HOME:
				mms = new MainMenuScreen(this);
				this.setScreen(mms);
				break;
			case PLAY:
				sms = new StageMenuScreen(this);
				this.setScreen(sms);
				break;
			case OPTIONS:
				oms = new OptionMenuScreen(this);
				this.setScreen(oms);
				break;
		}
	}

	@Override
	public SpriteBatch getBatch() {
		return batch;
	}
/*
	private Level createLevelTest(){
		Level level = new Level(3,3,new Position(0,0),new Position(2,2), Particule.State.CORPUSCULE)
	}*/
}
