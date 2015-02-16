package game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.Board;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class BoardView extends ShapeActor {
    private final int width;
    private final int height;

    public BoardView(Board board) {
        this(board.width, board.height);
    }

    public BoardView(int width, int height) {
        this.width = width;
        this.height = height;
        setColor(Color.LIGHT_GRAY);
    }
    @Override
    protected void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < width + 1; i++) {
            renderer.line(getX() + i * Board.CELL_SIZE, getY(), getX() + i * Board.CELL_SIZE, getY() + Board.CELL_SIZE * height);
        }
        for (int i = 0; i < height + 1; i++) {
            renderer.line(getX(), getY() + i * Board.CELL_SIZE, getX() + width * Board.CELL_SIZE, getY() + i * Board.CELL_SIZE);
        }
        renderer.end();
    }
}
