package com.noname.qn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Timer;
import java.util.TimerTask;

import com.noname.qn.screen.*;
import com.noname.qn.service.domain.IQNPreferences;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.gui.Gamable;
import com.noname.qn.utils.FileHandling;
import com.noname.qn.parameters.QNPreferences;

public class QNGame extends Game implements Gamable {
	private SpriteBatch batch;
	private SplashScreen splash;
	private MainMenuScreen mms;
	private StageMenuScreen sms;
	private OptionMenuScreen oms;
	private LevelScreen ls;
	private SplashScreen ss;
	private static int SPLASH_MINIMUM_MILLIS = 1000;
	private boolean bChange;
	private Timer time;

	public QNGame(){super();}

	@Override
	public void create () {
		batch = new SpriteBatch();
		time = new Timer();
		setScreen(new SplashScreen(this));
		final int splash_start_time = (int)System.currentTimeMillis();
		int splash_elapsed_time = (int)System.currentTimeMillis() - splash_start_time;
		IQNPreferences prefDTO = FileHandling.readPreferences();
		QNPreferences.getPref().setLanguage(prefDTO.getLanguage());
		QNPreferences.getPref().setEnableEffects(prefDTO.isEnableEffects());
		QNPreferences.getPref().setEnableMusic(prefDTO.isEnableMusic());
		QNPreferences.getPref().setDifficulty(prefDTO.getDifficulty());
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				bChange = true;
			}
		}, (QNGame.SPLASH_MINIMUM_MILLIS - splash_elapsed_time), 1000);
	}

	@Override
	public void render () {
		super.render();
		if (bChange){
			QNGame.this.setScreen(new MainMenuScreen(this));
			bChange = false;
			time.cancel();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void changeScreen(Type screen){
		switch(screen){
			case SPLASH:
				ss = new SplashScreen(this);
				this.setScreen(ss);
				break;
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

	@Override
	public void loadLevel(Levelable level) {
		ls = new LevelScreen(this,level);
		this.setScreen(ls);
	}
/*
	private Level createLevelTest(){
		Level level = new Level(3,3,new Position(0,0),new Position(2,2), Particule.State.CORPUSCULE)
	}*/
}
