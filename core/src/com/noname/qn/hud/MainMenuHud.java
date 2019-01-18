package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;

public class MainMenuHud extends QNHud {
    private Label playLabel;
    private Label optionLabel;
    private Label quitLabel;
    private Label titleLabel;

    public MainMenuHud(Gamer screen) {
        super(screen);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        stage.addActor(table);

        playLabel = new Label("Play", redStyle);
        optionLabel = new Label("Option", whiteStyle);
        quitLabel = new Label("Quit", whiteStyle);
        titleLabel = new Label("Welcome to Quantic Nightmare", whiteStyle);

        table.add(titleLabel).width(275).height(200);
        table.row();
        table.add(playLabel).width(75);
        table.row();
        table.add(optionLabel).width(75);
        table.row();
        table.add(quitLabel).width(75);

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

}
