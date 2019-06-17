package com.noname.qn.hud;

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

        soonAvailableTable = new FocusableTable("This level will be soon available !!!");
        soonAvailableTable.addLabel("Ok",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        });

        stagesTable = new FocusableTable("Choose your Nightmare");
        setDisplayedTable(stagesTable);

        stagesTable.addLabel("Back",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        });

        stagesTable.addImageButton("1f.png","1.png",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    screen.getGamable().loadLevel(LevelFactory.createLevel(Levelable.Levels.TEST) );
                } catch (IllegalLevelInsertionException e) {
                    //TODO handle error
                    e.printStackTrace();
                }catch (UnknownLevelException e) {
                    //TODO handle error
                    e.printStackTrace();
                }
            }
        }).size(40f, 40f);
        stagesTable.addImageButton("2f.png","2.png",new ClickListener() {
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
        },false).size(40f, 40f);


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

}

