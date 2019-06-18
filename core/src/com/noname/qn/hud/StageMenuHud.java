package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.entity.IllegalLevelInsertionException;
import com.noname.qn.entity.UnknownLevelException;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.*;

public class StageMenuHud extends QNMenuHud {
    private FocusableTable displayedTable;
    private FocusableTable stagesTable;
    private FocusableTable soonAvailableTable;
    public static Music effectSound = Gdx.audio.newMusic(Gdx.files.internal("effects/splat.mp3"));

    public StageMenuHud(Gamer screen) {
        super(screen);
        if (enableMusic) MainMenuHud.musicMenu.play();

        soonAvailableTable = new FocusableTable("This level will be soon available !!!");
        soonAvailableTable.addLabel("Ok",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        });

        stagesTable = new FocusableTable("Choose your Nightmare");
        setDisplayedTable(stagesTable);
        int nbLevel = 4;
        for (int i = 0; i < nbLevel; i++) {
            final int index = i+1;
            stagesTable.addImageButton(index+"f.png",index+".png",new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        screen.getGamable().loadLevel(LevelFactory.createLevel(index));
                    } catch (IllegalLevelInsertionException e) {
                        //TODO handle error
                        e.printStackTrace();
                    }catch (UnknownLevelException e) {
                        setDisplayedTable(soonAvailableTable);
                    }
                }
            },i%5==0).size(40f, 40f);
        }

        stagesTable.addLabel("Back",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        }).colspan(nbLevel);
        stagesTable.getCell(stagesTable.getTitleLabel()).colspan(nbLevel);


    }

    private void setDisplayedTable(FocusableTable table) {
        stage.clear();
        stage.addActor(table);
        displayedTable = table;
    }

    @Override
    protected void setFocus(Focusable actor) {
        displayedTable.setFocus(actor);
    }

    @Override
    protected void setNextFocus() {
        displayedTable.setNextFocus();
    }

    @Override
    protected void setPreviousFocus() {
        displayedTable.setPreviousFocus();
    }

    @Override
    protected Focusable getFocused() {
        return displayedTable.focused;
    }

    @Override
    void escaped() {
        if(displayedTable==stagesTable)
            screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
        else if(displayedTable == soonAvailableTable)
            setDisplayedTable(stagesTable);
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean result = super.keyUp(keycode);
        switch (keycode) {
            case Input.Keys.UP:
                if (enableEffects) effectSound.play();
                for (int i = 0; i < 4 ; i++) {
                    setPreviousFocus();
                }
                return true;
            case Input.Keys.DOWN:
                if (enableEffects) effectSound.play();
                for (int i = 0; i < 4 ; i++) {
                    setNextFocus();
                }
                return true;
            case Input.Keys.LEFT:
                if (enableEffects) effectSound.play();
                setPreviousFocus();
                return true;
            case Input.Keys.RIGHT:
                if (enableEffects) effectSound.play();
                setNextFocus();
                return true;
        }
        return result;

    }
}

