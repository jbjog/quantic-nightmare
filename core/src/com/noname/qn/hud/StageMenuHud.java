package com.noname.qn.hud;

import com.badlogic.gdx.Input;
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

        for (int i = 0; i < 2; i++) {
            final int index = i+1;
            stagesTable.addImageButton(index+"f.png",index+".png",new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        screen.getGamable().loadLevel(LevelFactory.createLevel(index) );
                    } catch (IllegalLevelInsertionException e) {
                        //TODO handle error
                        e.printStackTrace();
                    }catch (UnknownLevelException e) {
                        //TODO handle error
                        e.printStackTrace();
                    }
                }
            },i%5==0).size(40f, 40f);

        }
        /*stagesTable.addImageButton("2f.png","2.png",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //setDisplayedTable(soonAvailableTable);
                try {
                    screen.getGamable().loadLevel(LevelFactory.createLevel(Levelable.Levels.LEVEL_1) );
                } catch (IllegalLevelInsertionException e) {
                    //TODO handle error
                    e.printStackTrace();
                }catch (UnknownLevelException e) {
                    //TODO handle error
                    e.printStackTrace();
                }
            }
        },false).size(40f, 40f);*/

        stagesTable.addLabel("Back",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        });


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
                for (int i = 0; i < 4 ; i++) {
                    setPreviousFocus();
                }
                return true;
            case Input.Keys.DOWN:
                for (int i = 0; i < 4 ; i++) {
                    setNextFocus();
                }
                return true;
            case Input.Keys.LEFT:
                setPreviousFocus();
                return true;
            case Input.Keys.RIGHT:
                setNextFocus();
                return true;
        }
        return result;

    }
}

