package com.noname.qn.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableImageButton;
import com.noname.qn.utils.FocusableLabel;
import com.noname.qn.utils.Fonts;

public class StageMenuHud extends QNMenuHud {
    private FocusableImageButton level1;
    private FocusableImageButton level2;

    private Label titleLabel;
    private FocusableLabel backLabel;

    public StageMenuHud(Gamer screen) {
        super(screen);

        level1 = new FocusableImageButton("1f.png","1.png");
        level2 = new FocusableImageButton("2f.png","2.png");

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);

        titleLabel = new Label("Choose your Nightmare", Fonts.getUnFocusStyle());
        backLabel = new FocusableLabel("Back");

        table.add(titleLabel).width(275).height(200);
        table.row();
        table.add(backLabel);
        focusables.add(backLabel);
        setFocus(backLabel);
        table.row();
        table.add(level1).size(40f, 40f);
        focusables.add(level1);
        table.add(level2).size(40f, 40f);
        focusables.add(level2);

        backLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        });
        level1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Enter Level 1");
            }
        });
        level2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Enter Level 2");
            }
        });
    }

    @Override
    void echaped() {
        screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
    }

}

