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
import com.noname.qn.utils.QNPreferences;

public class QNGame extends Game implements Gamable {
	private SpriteBatch batch;
	private MainMenuScreen mms;
	private StageMenuScreen sms;
	private OptionMenuScreen oms;
	private LevelScreen ls;
    private boolean bChange;
    private boolean bChange2 = false;
    private Timer time;

	public QNGame(){super();}

	@Override
	public void create () {
		batch = new SpriteBatch();
		time = new Timer();
		setScreen(new SplashScreen(this));
		IQNPreferences prefDTO = FileHandling.readPreferences();
		QNPreferences.getPref().setLanguage(prefDTO.getLanguage());
		QNPreferences.getPref().setEnableEffects(prefDTO.isEnableEffects());
		QNPreferences.getPref().setEnableMusic(prefDTO.isEnableMusic());
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (!bChange){
					bChange = true;
				}
			}
		}, 1200, 1200);
	}

	@Override
	public void render () {
		super.render();
		if (bChange && bChange2){
			time.cancel();
			bChange = false;
			bChange2 = false;
			QNGame.this.setScreen(new MainMenuScreen(this));
		}
		if (bChange && !bChange2){
			setScreen(new SocietyScreen(this));
			bChange2 = true;
			bChange = false;
		}
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
