package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableLabel;
import com.noname.qn.utils.Fonts;


public class MainMenuHud extends QNMenuHud {
    private FocusableLabel playLabel;
    private FocusableLabel optionLabel;
    private FocusableLabel quitLabel;
    private Label titleLabel;

    public MainMenuHud(Gamer screen) {
        super(screen);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        stage.addActor(table);

        playLabel = new FocusableLabel("Play");
        optionLabel = new FocusableLabel("Option");
        quitLabel = new FocusableLabel("Quit");
        titleLabel = new Label("Welcome to Quantic Nightmare", Fonts.getUnFocusStyle());

        focusables.add(playLabel);
        focusables.add(optionLabel);
        focusables.add(quitLabel);

        setFocus(playLabel);

        table.add(titleLabel).height(200);
        table.row();
        table.add(playLabel);
        table.row();
        table.add(optionLabel);
        table.row();
        table.add(quitLabel);

        playLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.PLAY);
            }
        });

        optionLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.OPTIONS);
            }
        });

        quitLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    void echaped() {
        Gdx.app.exit();
    }


}
