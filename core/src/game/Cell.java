package game;

import game.actors.SquareShapeActor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Cell extends Group {
    public boolean isEmpty;

    public Cell() {
        SquareShapeActor square = new SquareShapeActor();
//        square.setColor(getColor(description.terrain));
        addActor(square);
        square.setSize(Board.CELL_SIZE - 4, Board.CELL_SIZE - 4);
    }

//    public Color getColor(Terrain terrain) {
//        switch (terrain) {
//            case forest:
//                return Color.valueOf("458C30");
//            case water:
//                return Color.valueOf("2E73AB");
//            case mountains:
//                return Color.valueOf("51423A");
//            case desert:
//                return Color.valueOf("FFDE00");
//        }
//        throw new IllegalStateException("no color for " + terrain);
//    }

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