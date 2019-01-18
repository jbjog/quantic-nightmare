package com.noname.qn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.qn.screen.MainMenuScreen;
import com.noname.qn.screen.OptionMenuScreen;
import com.noname.qn.screen.Screen;
import com.noname.qn.screen.StageMenuScreen;

public class QNGame extends Game {
	public SpriteBatch batch;
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

	public void changeScreen(Screen screen){
		switch(screen){
			case HOME:
				if(mms == null) mms = new MainMenuScreen(this);
				this.setScreen(mms);
				break;
			case PLAY:
				if(sms == null) sms = new StageMenuScreen(this);
				this.setScreen(sms);
				break;
			case OPTIONS:
				if(oms == null) oms = new OptionMenuScreen(this);
				this.setScreen(oms);
				break;
		}
	}
/*
	private Level createLevelTest(){
		Level level = new Level(3,3,new Position(0,0),new Position(2,2), Particule.State.CORPUSCULE)
	}*/
}
