package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.qn.QNGame;
import com.noname.qn.screen.Screen;
import com.noname.qn.utils.Fonts;

public class MainMenuHud implements Disposable {
    public QNGame game;
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;

    private Label.LabelStyle redStyle;
    private Label.LabelStyle whiteStyle;
    private Label playLabel;
    private Label optionLabel;
    private Label quitLabel;
    private Label titleLabel;

    public MainMenuHud(QNGame game) {
        this.game = game;
        viewport = new FitViewport(800, 480, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        font = Fonts.getDefaultFont();
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

        playLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Screen.PLAY);
            }
        });

        optionLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(Screen.OPTIONS);
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
    public void dispose() {
        stage.dispose();
    }
}
