package com.noname.qn.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.noname.qn.entity.Position;
import com.noname.qn.entity.Turn;
import com.noname.qn.service.domain.Enterable;
import com.noname.qn.service.domain.Levelable;
import com.noname.qn.service.domain.Movable;
import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.gui.Focusable;
import com.noname.qn.service.gui.Gamer;
import com.noname.qn.service.gui.ScreenChanger;
import com.noname.qn.utils.FocusableTable;
import com.noname.qn.utils.Fonts;

import java.util.ArrayList;
import java.util.List;

public class LevelHud extends QNMenuHud{
    private Table displayedTable;
    final private Levelable level;
    private Table main;
    private Table boardTable;
    private Table arrowTable;
    private FocusableTable gameOverTable;
    private Label gameOverState;
    private FocusableTable successTable;
    private boolean paused = false;
    private List<Movable.Direction> moves = new ArrayList<>();

    public LevelHud(Gamer screen, Levelable level) {
        super(screen);
        this.level = level;
        buildGameOverTable();
        buildSuccessTable();
        //table principale composé de la map et de la zones pour les flèches
        main = new Table();
        displayedTable=main;
        main.top();
        main.setFillParent(true);

        boardTable = new Table();
        arrowTable = new Table();
        main.add(boardTable);
        main.row();
        main.add(arrowTable);
        stage.addActor(main);
        display();
    }
    //construction de l'ecran gameOver
    private void buildGameOverTable(){
        gameOverTable = new FocusableTable("GameOver",100);
        gameOverTable.getTitleLabel().setStyle(Fonts.getBigRedStyle());

        //ajout du message
        gameOverTable.row();
        Label gameOverMessage = new Label("Votre tour se termine en etat",new Label.LabelStyle(Fonts.getDefaultFont(25),Color.WHITE));;
        gameOverTable.add(gameOverMessage).height(100).padBottom(20);
        //ajout de l'etat final'
        gameOverTable.row();
        gameOverState = new Label("",new Label.LabelStyle(Fonts.getDefaultFont(25),Color.WHITE));
        gameOverTable.add(gameOverState).height(100).padBottom(20);
        //ajout d'un background gris transparent à l'écran
        Pixmap bgTable = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        bgTable.setColor(new Color(0,0,0,0.4f));
        bgTable.fill();
        gameOverTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(bgTable))));

        gameOverTable.addLabel("Retry",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                level.reset();
                hideGameOverTable();
            }
        },true,true);
        gameOverTable.addLabel("Quit",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.getGamable().changeScreen(ScreenChanger.Type.PLAY);
            }
        });
    }


    //affichage de l'ecran de Game Over par superposition au board du jeu
    private void showGameOverTable(){
        Stack s = new Stack();
        s.setFillParent(true);
        s.add(main);
        s.add(gameOverTable);
        gameOverState.setAlignment(Align.center);

        Playable.State lastState = level.getLastState();
        gameOverState.setText(lastState.toString());
        //choix de la couleur selon l'état
        switch (lastState){
            case LOOSE:
                gameOverState.getStyle().fontColor = Color.RED;
                break;
            case WIN:
                gameOverState.getStyle().fontColor = Color.GREEN;
                break;
        }
        stage.clear();
        stage.addActor(s);
        displayedTable=gameOverTable;
    }
    private void hideGameOverTable(){
        stage.clear();
        stage.addActor(main);
        displayedTable=main;
        display();

    }
    private void buildSuccessTable(){

    }
    private void display() {
        displayBoard();
        displayArrows();
    }

    //affiche les case et la particule
    private void displayBoard() {
        boardTable.clearChildren();
        for (int i = 0; i < level.getNbRows(); i++) {
            boardTable.row();
            for (int j = 0; j < level.getNbColumns(); j++) {
                boolean found = false;
                Stack s = new Stack();
                for (Enterable e : level.getSquares()) {
                    if (e.getPosition().equals(new Position(j, i))) {
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

    //affiche les flèches saisi par l'utilisateur
    private void displayArrows(){
        arrowTable.clearChildren();
        if(moves.isEmpty())
            arrowTable.add(new Image(new Texture("black.png")));
        else{
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
    }

    @Override
    public boolean keyUp(int keycode) {
        //gestion du clavier pendant le game play
        if(displayedTable==main)
            return playingKeyUp(keycode);
        //gestion du clavier lors des menus classiques
        else if(displayedTable==gameOverTable)
            return super.keyUp(keycode);
        return false;
    }

    //execute le prochain déplacement
    private void nextMove(){
        Playable.State result = Playable.State.CONTINUE;
        if(moves.size()>0){
            result = level.play(moves.get(0));
            moves.remove(0);
            displayBoard();
        }
        //si plus de déplacement ou que la partie est perdu affichage du game over
        if(moves.size()==0 || result == Playable.State.LOOSE)
            endTry();
    }

    private void executeMoves() {
        for (int i = 0; i < moves.size(); i++) {
            Movable.Direction d = moves.get(i);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Playable.State lastState = level.getLastState();
                    if (lastState == Playable.State.CONTINUE) {
                        level.play(d);
                        if (level.getTracker().size() == moves.size()) {
                            endTry();
                        }
                    } else if (lastState == Playable.State.LOOSE || lastState == Playable.State.WIN) {
                        endTry();
                    }
                    displayBoard();
                }
            }, i+1);

        }
    }

    private void endTry(){
        moves.clear();
        paused = false;
        //affichages des cases
        displayBoard();
        arrowTable.clearChildren();
        //affichage du tracker des déplacements
        if(level.getTracker().isEmpty())
            arrowTable.add(new Image(new Texture("black.png")));
        else{
            for (Turn t : level.getTracker()){
                arrowTable.add(new Image(t.getTexture()));
            }
        }
        showGameOverTable();
    }

    //gestion du clavier lors du gamePlay
    private boolean playingKeyUp(int keycode){
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
                    //nextMove();
                    executeMoves();
                    break;
                //Echap
                case 131:
                    echaped();
                    break;
                default:
                    return false;
            }
            return true;
        }
        else{
            switch (keycode) {
                case 66:
                    nextMove();
                    return true;
            }
        }
        return false;
    }

    @Override
    protected Focusable getFocused() {
        return gameOverTable.focused;
    }

    @Override
    protected void setFocus(Focusable actor) {
        gameOverTable.setFocus(actor);
    }

    @Override
    protected void setNextFocus() {
        gameOverTable.setNextFocus();
    }

    @Override
    protected void setPreviousFocus() {
        gameOverTable.setPreviousFocus();
    }

    @Override
    void echaped() {
        if (displayedTable==main)
            screen.getGamable().changeScreen(ScreenChanger.Type.PLAY);
        else if(displayedTable==gameOverTable)
            hideGameOverTable();
    }
}
