package game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.Board;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class BoardView extends ShapeActor {
    private final Board board;

    public BoardView(Board board) {
        this.board = board;
        setColor(Color.LIGHT_GRAY);
    }

    @Override
    protected void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < board.width + 1; i++) {
            renderer.line(getX() + i * Board.CELL_SIZE, getY(), getX() + i * Board.CELL_SIZE, getY() + Board.CELL_SIZE * board.height);
        }
        for (int i = 0; i < board.height + 1; i++) {
            renderer.line(getX(), getY() + i * Board.CELL_SIZE, getX() + board.width * Board.CELL_SIZE, getY() + i * Board.CELL_SIZE);
        }
        renderer.end();
    }
}
