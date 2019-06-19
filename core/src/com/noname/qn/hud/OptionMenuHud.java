package com.noname.qn.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableTable;

public class OptionMenuHud extends QNMenuHud {
    private FocusableTable table;

    public OptionMenuHud(Gamer screen) {
        super(screen);
        table = new FocusableTable("OPTIONS");
        stage.addActor(table);
        ClickListener voidListener = new ClickListener(){};
        table.addLabel("Toggle Sound",new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
              if (enableMusic) {
                  MainMenuHud.musicMenu.stop();
                  enableMusic = false;
              } else {
                  MainMenuHud.musicMenu.play();
                  enableMusic = true;
              }
          }
        });
        table.addLabel("Toggle Effects",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (enableEffects) {
                    enableEffects = false;
                } else {
                    enableEffects = true;
                }
            }
        });
        table.addLabel("Language",voidListener);
        table.addLabel("Back",new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        });
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

