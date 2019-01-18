package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;

public class StageMenuHud extends QNHud {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    private Label titleLabel;
    private Label backLabel;

    public StageMenuHud(Gamer screen) {
        super(screen);

        myTexture = new Texture(Gdx.files.internal("1.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        stage.addActor(table);

        titleLabel = new Label("Choose your Nightmare", whiteStyle);
        backLabel = new Label("Back", whiteStyle);

        table.add(titleLabel).width(275).height(200);
        table.row();
        table.add(button).size(40f, 40f);
        table.row();
        table.add(backLabel);

        backLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.HOME);
            }
        });
    }


}

