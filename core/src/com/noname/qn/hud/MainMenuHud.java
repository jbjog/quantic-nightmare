package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableTable;
import com.noname.qn.utils.QNPreferences;
import com.noname.qn.utils.TextValues;

public class MainMenuHud extends QNMenuHud {
    private FocusableTable displayedTable;
    private FocusableTable mainTable;
    private FocusableTable exitTable;
    public static Music musicMenu = Gdx.audio.newMusic(Gdx.files.internal("effects/heartbeat.mp3"));
    public static Music effectSound = Gdx.audio.newMusic(Gdx.files.internal("effects/splat.mp3"));

    public MainMenuHud(Gamer screen) {
        super(screen);

        if (QNPreferences.getPref().isEnableMusic()) musicMenu.play();
        musicMenu.setLooping(true);

        mainTable = new FocusableTable(TextValues.WELCOME[QNPreferences.getPref().getLanguage()]+
                TextValues.APP_NAME[QNPreferences.getPref().getLanguage()],200);
        setDisplayedTable(mainTable);
        mainTable.addLabel(TextValues.PLAY[QNPreferences.getPref().getLanguage()],new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.PLAY);
            }
        });
        mainTable.addLabel(TextValues.OPTION[QNPreferences.getPref().getLanguage()],new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.OPTIONS);
            }
        });
        mainTable.addLabel(TextValues.QUIT[QNPreferences.getPref().getLanguage()],new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setDisplayedTable(exitTable);
            }
        });

        exitTable = new FocusableTable(TextValues.CONFIRM[QNPreferences.getPref().getLanguage()]);
        exitTable.addLabel(TextValues.YES[QNPreferences.getPref().getLanguage()],new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        exitTable.addLabel(TextValues.NO[QNPreferences.getPref().getLanguage()],new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setDisplayedTable(mainTable);
            }
        });

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
        if(displayedTable==exitTable)
            setDisplayedTable(mainTable);
        else if(displayedTable == mainTable)
            setDisplayedTable(exitTable);
    }

    private void setDisplayedTable(FocusableTable table){
        stage.clear();
        stage.addActor(table);
        displayedTable = table;
    }
}
