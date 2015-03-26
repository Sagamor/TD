package game.controllers;

import game.Marker;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import game.Board;
import game.Config;
import game.Cell;

/**
 * Created by Sagamor on 08/04/2014.
 */
public class UIController {
    Stage stage;
    Board board;

    Table wrapperTable = new Table();

    public UIController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
        board.ui = this;

        wrapperTable.setTransform(true);
        wrapperTable.setFillParent(true);
        board.setScale(0.85f);
        board.setSize(board.getWidth() * board.getScaleX(), board.getHeight() * board.getScaleY());
        stage.addActor(board);
        wrapperTable.add(board).expand(2,1).align(Align.center);

        stage.addActor(wrapperTable);
    }
}
