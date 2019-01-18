package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuHud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;

    private Label.LabelStyle redStyle;
    private Label.LabelStyle whiteStyle;
    private Label playLabel;
    private Label optionLabel;
    private Label quitLabel;
    private Label titleLabel;

    public MainMenuHud(SpriteBatch sb) {
        viewport = new FitViewport(800, 480, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Gdx.input.setInputProcessor(stage);

        font = this.getFont();
        redStyle = new Label.LabelStyle(font, Color.RED);
        whiteStyle = new Label.LabelStyle(font, Color.WHITE);

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

        quitLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private BitmapFont getFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("theDarkFont.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 20;
        font = generator.generateFont(parameter);
        return font;
    }
}
