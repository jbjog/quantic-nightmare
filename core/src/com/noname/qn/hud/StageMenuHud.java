package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.qn.QNGame;
import com.noname.qn.screen.Screen;

public class StageMenuHud implements Disposable {
    public QNGame game;
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;

    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    private Label.LabelStyle redStyle;
    private Label.LabelStyle whiteStyle;
    private Label titleLabel;
    private Label backLabel;

    public StageMenuHud(QNGame game) {
        this.game = game;
        viewport = new FitViewport(800, 480, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        myTexture = new Texture(Gdx.files.internal("1.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);

        font = this.getFont();
        redStyle = new Label.LabelStyle(font, Color.RED);
        whiteStyle = new Label.LabelStyle(font, Color.WHITE);

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
                game.changeScreen(Screen.HOME);
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

