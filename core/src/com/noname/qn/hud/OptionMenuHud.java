package com.noname.qn.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.parameters.QNPreferences;
import com.noname.qn.parameters.TextValues;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.*;


public class OptionMenuHud extends QNMenuHud {
    private FocusableTable table;
    //store label for translation
    private Label labelSound;
    private Label labelSoundValue;
    private Label labelEffect;
    private Label labelEffectValue;
    private Label labelLanguage;
    private Label labelLanguageValue;
    private Label labelDifficulty;
    private Label labelDifficultyValue;
    private Label labelBack;


    public OptionMenuHud(Gamer screen) {
        super(screen);
        table = new FocusableTable(TextValues.OPTION[QNPreferences.getPref().getLanguage().ordinal()]);
        stage.addActor(table);
        table.getCell(table.getTitleLabel()).colspan(2);
        labelSound = (Label)table.addLabel("",new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
              if (QNPreferences.getPref().isEnableMusic()) {
                  MainMenuHud.musicMenu.stop();
              } else {
                  MainMenuHud.musicMenu.play();
              }
              QNPreferences.getPref().setEnableMusic(!QNPreferences.getPref().isEnableMusic());
              updateTexts();
          }
        }).padRight(30).getActor();
        labelSoundValue = table.add(new Label("", Fonts.getUnFocusStyle())).getActor();
        labelEffect = (Label)table.addLabel("",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                QNPreferences.getPref().setEnableEffects(!QNPreferences.getPref().isEnableEffects());
                updateTexts();
            }
        }).padRight(30).getActor();
        labelEffectValue = table.add(new Label("", Fonts.getUnFocusStyle())).getActor();
        labelLanguage = (Label)table.addLabel("",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int newLanguageIndex = (QNPreferences.getPref().getLanguage().ordinal()+1)%TextValues.Language.values().length;
                QNPreferences.getPref().setLanguage(TextValues.Language.values()[newLanguageIndex]);
                updateTexts();
            }
        }).padRight(30).getActor();
        labelLanguageValue = table.add(new Label("", Fonts.getUnFocusStyle())).getActor();
        labelDifficulty = (Label)table.addLabel("",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int newDifficultyIndex = (QNPreferences.getPref().getDifficulty().ordinal()+1)% Gamable.Difficulty.values().length;
                QNPreferences.getPref().setDifficulty(Gamable.Difficulty.values()[newDifficultyIndex]);
                updateTexts();
            }
        }).padRight(30).getActor();
        labelDifficultyValue = table.add(new Label("", Fonts.getUnFocusStyle())).getActor();
        labelBack = (Label)table.addLabel("",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                QNPreferences.getPref().save();
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        }).colspan(2).getActor();
        updateTexts();
    }

    private void updateTexts(){
        labelSound.setText(TextValues.SOUND[QNPreferences.getPref().getLanguage().ordinal()]);
        if(QNPreferences.getPref().isEnableMusic()){
            labelSoundValue.setText(TextValues.ENABLE[QNPreferences.getPref().getLanguage().ordinal()]);
        }else{
            labelSoundValue.setText(TextValues.DISABLE[QNPreferences.getPref().getLanguage().ordinal()]);
        }
        labelEffect.setText(TextValues.EFFECT[QNPreferences.getPref().getLanguage().ordinal()]);
        if(QNPreferences.getPref().isEnableEffects()){
            labelEffectValue.setText(TextValues.ENABLE[QNPreferences.getPref().getLanguage().ordinal()]);
        }else{
            labelEffectValue.setText(TextValues.DISABLE[QNPreferences.getPref().getLanguage().ordinal()]);
        }
        labelLanguage.setText(TextValues.LANGUAGE[QNPreferences.getPref().getLanguage().ordinal()]);
        labelLanguageValue.setText(TextValues.LANGUAGE_NAME[QNPreferences.getPref().getLanguage().ordinal()]);
        labelDifficulty.setText(TextValues.DIFFICULTY[QNPreferences.getPref().getLanguage().ordinal()]);
        labelDifficultyValue.setText(
                TextValues.getDifficultyString(
                        QNPreferences.getPref().getDifficulty(),QNPreferences.getPref().getLanguage())
        );
        labelBack.setText(TextValues.BACK[QNPreferences.getPref().getLanguage().ordinal()]);
        //
        // TextValues.EFFECT[QNPreferences.getPref().getLanguage()]
        // TextValues.LANGUAGE[QNPreferences.getPref().getLanguage()]
        // TextValues.BACK[QNPreferences.getPref().getLanguage()]
    }
    @Override
    protected void setFocus(Focusable actor) {
        table.setFocus(actor);
    }

    @Override
    protected void setNextFocus() {
        table.setNextFocus();
    }

    @Override
    protected void setPreviousFocus() {
        table.setPreviousFocus();
    }

    @Override
    protected Focusable getFocused() {
        return table.focused;
    }

    @Override
    void escaped() {
        screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
    }

}

