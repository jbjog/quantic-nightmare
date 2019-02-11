package com.noname.qn.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.noname.qn.entity.Position;
import com.noname.qn.entity.Turn;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LevelHud extends QNHud implements InputProcessor {

    private Levelable level;
    private Table boardTable;
    private Table arrowTable;
    private boolean paused = false;
    private List<Movable.Direction> moves = new ArrayList<>();

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
        displayBoard();
        displayArrows();
    }

    private void displayBoard() {

        boardTable.clearChildren();

        for (int i = 0; i < level.getNbRows(); i++) {
            boardTable.row();
            for (int j = 0; j < level.getNbColumns(); j++) {
                boolean found = false;
                Stack s = new Stack();
                for (Enterable e : level.getSquares()) {
                    if (e.getPosition().equals(new Position(j, i))) {
                        //new Image();
                        s.add(new Image(e.getTexture()));
                        found = true;
                    }
                    if (level.getPlayer().getPosition().equals(new Position(j, i)))
                        s.add(new Image(level.getPlayer().getTexture()));
                }
                if (!found) {
                    s.add(new Image(new Texture("black.png")));
                }
                boardTable.add(s);
            }
        }
    }
    private void displayArrows(){
        arrowTable.clearChildren();
        for (Movable.Direction d : moves){
            switch (d) {
                case DOWN:
                    arrowTable.add(new Image(new Texture("down.png")));
                    break;
                case UP:
                    arrowTable.add(new Image(new Texture("up.png")));
                    break;
                case RIGHT:
                    arrowTable.add(new Image(new Texture("right.png")));
                    break;
                case LEFT:
                    arrowTable.add(new Image(new Texture("left.png")));
                    break;
            }

        }

    }

    @Override
    public boolean keyUp(int keycode) {
        if (!paused) {
            switch (keycode) {
                //UP
                case 19:
                    moves.add(Movable.Direction.UP);
                    display();
                    break;
                //DOWN
                case 20:
                    moves.add(Movable.Direction.DOWN);
                    display();
                    break;
                //LEFT
                case 21:
                    moves.add(Movable.Direction.LEFT);
                    display();
                    break;
                //RIGHT
                case 22:
                    moves.add(Movable.Direction.RIGHT);
                    display();
                    break;
                //Enter
                case 66:
                    paused = true;
                    executeMoves();
                    break;
                case 131:
                    screen.getGamable().changeScreen(ScreenChanger.Type.PLAY);
                    break;
                default:
                    return false;
            }
            return true;
        }
        else return false;
    }

    private void executeMoves() {
        //TODO delay turn execution
        for (Movable.Direction d : moves) {
               Playable.State result = level.play(d);
               if (result == Playable.State.LOOSE)
                   break;
               display();
        }
        moves.clear();
        paused = false;
        System.out.println(level.getTracker().get(level.getTracker().size()-1));
        displayBoard();
        arrowTable.clearChildren();
        for (Turn t : level.getTracker()){
            arrowTable.add(new Image(t.getTexture()));
        }
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
