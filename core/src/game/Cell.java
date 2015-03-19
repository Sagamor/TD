package game;

import game.actors.SquareShapeActor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Cell extends Group {
    public boolean isEmpty;

    public Cell() {
        SquareShapeActor square = new SquareShapeActor();
//        square.setColor(getColor(description.terrain));
        addActor(square);
        square.setSize(Board.CELL_SIZE - 4, Board.CELL_SIZE - 4);
    }

    public void occupy(Board board) {
        clearChildren();
        isEmpty = false;
//        description.place.visit(this, board);
//        Image tile = new Image(Config.skin.getDrawable(description.terrain + "/" + description.place));
//        tile.setSize(Board.CELL_SIZE - 4, Board.CELL_SIZE - 4);
//        addActor(tile);
    }

    @Override public String toString() {
        return "Field{" +
            ", isEmpty=" + isEmpty +
            '}';
    }
}