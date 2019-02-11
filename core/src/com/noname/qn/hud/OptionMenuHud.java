package com.noname.qn.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableLabel;
import com.noname.qn.utils.Fonts;

public class OptionMenuHud extends QNMenuHud {
    private FocusableLabel soundLabel;
    private FocusableLabel  effectsLabel;
    private FocusableLabel  languageLabel;
    private FocusableLabel  backLabel;
    private Label titleLabel;

    public OptionMenuHud(Gamer screen) {
        super(screen);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        stage.addActor(table);

        soundLabel = new FocusableLabel ("Enable Sound");
        effectsLabel = new FocusableLabel ("Enable Effects");
        languageLabel = new FocusableLabel ("Language");
        backLabel = new FocusableLabel ("Back");
        titleLabel = new Label("OPTIONS", Fonts.getUnFocusStyle());

        table.add(titleLabel).width(275).height(200);
        table.row();
        table.add(soundLabel).width(75);
        focusables.add(soundLabel);
        table.row();
        table.add(effectsLabel).width(75);
        focusables.add(effectsLabel);
        table.row();
        table.add(languageLabel).width(75);
        focusables.add(languageLabel);
        table.row();
        table.add(backLabel).width(75);
        focusables.add(backLabel);

        backLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        });
    }

    @Override
    void echaped() {
        screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
    }

}

