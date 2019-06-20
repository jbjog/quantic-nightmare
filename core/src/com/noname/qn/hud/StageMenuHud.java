package com.noname.qn.hud;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.noname.qn.entity.IllegalLevelInsertionException;
import com.noname.qn.entity.UnknownLevelException;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.*;

import java.lang.management.ManagementFactory;

import static com.noname.qn.service.domain.Levelable.Result.*;

public class StageMenuHud extends QNMenuHud {
    private FocusableTable displayedTable;
    private FocusableTable stagesTable;
    private FocusableTable messageTable;

    public StageMenuHud(Gamer screen) {
        super(screen);
        if (QNPreferences.getPref().isEnableMusic()) MainMenuHud.musicMenu.play();

        messageTable = new FocusableTable("");
        messageTable.addLabel("Ok",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                escaped();
            }
        });

        stagesTable = new FocusableTable(TextValues.CHOOSE[QNPreferences.getPref().getLanguage()]);
        setDisplayedTable(stagesTable);
        int nbLevel = 20;
        for (int i = 0; i < nbLevel; i++) {
            final int index = i+1;
            String imagePath = "numbers/"+index+".png";
            try{
                imagePath = getLevelImagePath(index);

            }catch (GdxRuntimeException e){
                //default image is already define above
            }
            stagesTable.addImageButton("numbers/"+index+"f.png",imagePath,new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        screen.getGamable().loadLevel(LevelFactory.createLevel(index));
                    } catch (Exception e) {
                        if(e instanceof IllegalLevelInsertionException){
                            messageTable.getTitleLabel().setText(TextValues.LEVEL_BROKEN[QNPreferences.getPref().getLanguage()]);
                        }else if(e instanceof UnknownLevelException){
                            messageTable.getTitleLabel().setText(TextValues.LEVEL_NOT_AVAILABLE[QNPreferences.getPref().getLanguage()]);
                        }
                        setDisplayedTable(messageTable);
                        boolean isDebug =
                                ManagementFactory.getRuntimeMXBean().
                                        getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
                        if(isDebug)
                            e.printStackTrace();
                    }
                }
            },i%5==0).size(40f, 40f).pad(10);
        }
        stagesTable.addLabel(TextValues.BACK[QNPreferences.getPref().getLanguage()],new ClickListener() {
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
        else if(displayedTable == messageTable)
            setDisplayedTable(stagesTable);
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean result = super.keyUp(keycode);
        switch (keycode) {
            case Input.Keys.UP:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                for (int i = 0; i < 4 ; i++) {
                    setPreviousFocus();
                }
                return true;
            case Input.Keys.DOWN:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                for (int i = 0; i < 4 ; i++) {
                    setNextFocus();
                }
                return true;
            case Input.Keys.LEFT:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                setPreviousFocus();
                return true;
            case Input.Keys.RIGHT:
                if (QNPreferences.getPref().isEnableEffects()) effectSound.play();
                setNextFocus();
                return true;
        }
        return result;
    }

    private static String getLevelImagePath(int levelNumber){
        String result =levelNumber+"";
        //acces aux données pour connaitre le niveau
        PlayerScore ps = FileHandling.readScore(levelNumber);
        double percent = ps.getScore()==0 ? 0 : (double)ps.getMinMove()/ps.getScore();
        Levelable.Result best =  Levelable.Result.getResultFromPercent(percent);
        if (best.compareTo(SUCCEED)==0){
                result += "g";
        }else if(best.compareTo(BRONZE)==0){
            result += "b";
        }else if(best.compareTo(SILVER)==0){
            result += "s";
        }else if(best.compareTo(GOLD)==0){
            result += "go";
        }
        return "numbers/" + result+".png";
    }
}

