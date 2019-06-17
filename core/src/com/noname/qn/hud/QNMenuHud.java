package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;

public abstract class QNMenuHud extends QNHud implements InputProcessor {

    public QNMenuHud(Gamer screen) {
        super(screen);
        InputMultiplexer multiplexer = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    abstract protected Focusable getFocused();
    abstract protected void setFocus(Focusable actor);
    abstract protected void setNextFocus();
    abstract protected void setPreviousFocus();
    abstract void escaped();

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            //UP
            case 19:
                setPreviousFocus();
                return true;
            //DOWN
            case 20:
                setNextFocus();
                return true;
            //Enter
            case 66:
                getFocused().getAction().clicked(null, 0, 0);
                return true;

            case 131:
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
