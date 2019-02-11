package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.entity.Position;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.gui.Gamer;

public class LevelHud extends QNHud implements InputProcessor {

    private Levelable level;
    private Table boardTable;
    private Table arrowTable;
    private boolean paused = false;

    public LevelHud(Gamer screen, Levelable level) {
        super(screen);
        this.level = level;
        InputMultiplexer multiplexer = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(multiplexer);

        Table main = new Table();;
        main.top();
        main.setFillParent(true);

        boardTable = new Table();
        //stage.addActor(boardTable);

        arrowTable = new Table();
        //arrowTable.bottom();
        main.add(boardTable);
        main.row();
        main.add(arrowTable);
        stage.addActor(main);

        display();
    }

    private void display() {

        boardTable.clearChildren();

        for (int i = 0; i < level.getNbRows(); i++) {
            boardTable.row();
            for (int j = 0; j < level.getNbColumns(); j++) {
                boolean found = false;
                Stack s = new Stack();
                for (Enterable e:level.getSquares()) {
                    if(e.getPosition().equals(new Position(j,i))){
                        //new Image();
                        s.add(new Image(e.getTexture()));
                        found=true;
                    }
                    if(level.getPlayer().getPosition().equals(new Position(j,i)))
                        s.add(new Image(level.getPlayer().getTexture()));
                }
                if(!found){
                    s.add(new Image(new Texture("black.png")));
                }
                boardTable.add(s);
            }
        }

    }

    @Override
    public boolean keyUp(int keycode) {
        if (!paused) {
            switch (keycode) {
                //UP
                case 19:
                    level.play(Movable.Direction.UP);
                    arrowTable.add(new Image(new Texture("up.png")));
                    break;
                //DOWN
                case 20:
                    level.play(Movable.Direction.DOWN);
                    arrowTable.add(new Image(new Texture("down.png")));
                    break;
                //LEFT
                case 21:
                    level.play(Movable.Direction.LEFT);
                    arrowTable.add(new Image(new Texture("left.png")));
                    break;
                //RIGHT
                case 22:
                    level.play(Movable.Direction.RIGHT);
                    arrowTable.add(new Image(new Texture("right.png")));
                    break;
                //Enter
                case 66:
                    paused = true;
                    break;
                case 131:
                    break;
                default:
                    return false;
            }
            display();
            return true;
        }
        else return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
