package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;

import java.util.ArrayList;
import java.util.List;

public class QNMenuHud extends QNHud implements InputProcessor {
    protected List<Focusable> focusables = new ArrayList<>();
    protected Focusable focused;

    public QNMenuHud(Gamer screen) {
        super(screen);
        InputMultiplexer multiplexer = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    protected void setFocus(Focusable actor){
        focused = actor;
        for (Focusable l :focusables){
            l.setFocus(l.equals(actor));
        }
    }
    protected void setNextFocus(){
        int i = focusables.indexOf(focused);
        if(i!=focusables.size()-1)
            setFocus(focusables.get(i+1));
        stage.draw();
    }
    protected void setPreviousFocus(){
        int i = focusables.indexOf(focused);
        if(i>0)
            setFocus(focusables.get(i-1));
        stage.draw();

    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
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
                for(EventListener e : focused.getListeners()){
                    if(e instanceof ClickListener){
                        ((ClickListener) e).clicked(null, 0, 0);
                    }
                }
                return true;
            default:
                return false;
        }
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
