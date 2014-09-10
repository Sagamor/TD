package game.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.Board;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class BoardView extends ShapeActor {
    private final Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    @Override
    protected void draw(ShapeRenderer renderer) {

    }
}
