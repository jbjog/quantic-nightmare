package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.parameters.QNPreferences;

public abstract class QNMenuHud extends QNHud implements InputProcessor {
    public static Music effectSound = Gdx.audio.newMusic(Gdx.files.internal("effects/pop.mp3"));

    public QNMenuHud(Gamer screen) {
        super(screen);
        InputMultiplexer multiplexer = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(multiplexer);

        effectSound.setVolume(0.3f);

    }

    abstract protected Focusable getFocused();
    abstract protected void setFocus(Focusable actor);
    abstract protected void setNextFocus();
    abstract protected void setPreviousFocus();
    abstract void escaped();

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                setPreviousFocus();
                return true;
            case Input.Keys.DOWN:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                setNextFocus();
                return true;
            case Input.Keys.ENTER:
                getFocused().getAction().clicked(null, 0, 0);
                return true;
            case Input.Keys.ESCAPE:
                escaped();
                return true;
            default:
                return false;
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
