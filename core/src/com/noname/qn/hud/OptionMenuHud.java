package com.noname.qn.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;

public class OptionMenuHud extends QNHud{
    private Label soundLabel;
    private Label effectsLabel;
    private Label languageLabel;
    private Label backLabel;
    private Label titleLabel;

    public OptionMenuHud(Gamer screen) {
        super(screen);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        stage.addActor(table);

        soundLabel = new Label("Enable Sound", redStyle);
        effectsLabel = new Label("Enable Effects", whiteStyle);
        languageLabel = new Label("Language", whiteStyle);
        backLabel = new Label("Back", whiteStyle);
        titleLabel = new Label("OPTIONS", whiteStyle);

        table.add(titleLabel).width(275).height(200);
        table.row();
        table.add(soundLabel).width(75);
        table.row();
        table.add(effectsLabel).width(75);
        table.row();
        table.add(languageLabel).width(75);
        table.row();
        table.add(backLabel).width(75);

        backLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        });
    }

}

