package game;

import TUIO.TuioObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.IntMap;
import game.actors.CircleShapeActor;

/**
 * Created by  Sagamor on 24/03/2014.
 */

public class Marker extends Group {

    public TuioObject tobj;

    private static IntMap<String> idToFileName = new IntMap<String>();

    static {
        idToFileName.put(109, "green"); // green tower
        idToFileName.put(110, "blue"); // blue tower
        idToFileName.put(115, "yellow"); // yellow tower
        idToFileName.put(117, "red"); // red tower
        idToFileName.put(116, "castle"); // castle
    }

    public Board board;
    public Coordinate coordinate;

    public int getId() {
        return tobj.getSymbolID();
    }

    public Marker(TuioObject tobj) {
        this.tobj = tobj;
        Image image = createImage(tobj.getSymbolID());
        if (tobj.getSymbolID() != 116) {
            floating(image);
        }
        addActor(image);
    }

    public static Image createImage(int id) {
        Image image;
        if (id == 116) {
            image = new Image(Config.skin, "objects/" + idToFileName.get(id));
            image.setSize(Board.CELL_SIZE * 1.3f, Board.CELL_SIZE * 1.3f);
        } else {
            image = new Image(Config.skin, "objects/tower_" + idToFileName.get(id));
            image.setSize(Board.CELL_SIZE, Board.CELL_SIZE);
        }
        image.setPosition(Board.CELL_SIZE / 2f - image.getWidth() / 2f, Board.CELL_SIZE / 2f - image.getHeight() / 2f);
        image.setOrigin(image.getWidth() / 2f, image.getHeight() / 2f);
        return image;
    }

    public CircleShapeActor createCircle() {
        CircleShapeActor circleNew = new CircleShapeActor();
        circleNew.setSize(Board.CELL_SIZE / 3f, Board.CELL_SIZE / 3f);
        circleNew.setPosition(Board.CELL_SIZE / 2f - circleNew.getWidth() / 2f, Board.CELL_SIZE / 2f - circleNew.getHeight() / 2f);
        circleNew.setColor(0, 0, 0, 0);
        circleNew.setColor(getColor(idToFileName.get(tobj.getSymbolID())));
        addActor(circleNew);
        return circleNew;
    }

    public static void floating(final Image tower) {
        tower.addAction(Actions.sequence(
                Actions.scaleTo(0.92f, 0.92f, 1.5f),
                Actions.delay(0.25f),
                Actions.scaleTo(1.0f, 1.0f, 2.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        floating(tower);
                    }
                })
        ));
    }

    public static void animate(final Actor circle) {
        circle.addAction(Actions.sequence(
                Actions.alpha(0.5f),
                Actions.parallel(
                        Actions.sizeTo(Board.CELL_SIZE * 0.8f, Board.CELL_SIZE * 0.8f, 1.3f),
                        Actions.moveBy(-Board.CELL_SIZE / 4f, -Board.CELL_SIZE / 4f, 1.3f),
                        Actions.alpha(0, 1.25f)
                ),
                Actions.sizeTo(Board.CELL_SIZE / 3f, Board.CELL_SIZE / 3f),
                Actions.moveBy(Board.CELL_SIZE / 4f, Board.CELL_SIZE / 4f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle);
                    }
                })
        ));
    }

    public String getTowerColorName() {
        return idToFileName.get(getId());
    }

    public Color getColor(String playerId) {
        if (playerId.equals("green")) {
            return Color.valueOf("90ec00");
        } else if (playerId.equals("blue")) {
            return Color.valueOf("001199");
        } else if (playerId.equals("yellow")) {
            return Color.valueOf("ffdd00");
        } else if (playerId.equals("red")) {
            return Color.valueOf("aa0000");
        } else if (playerId.equals("castle")) {
            return Color.valueOf("000000");
        }
        throw new IllegalStateException("no color for " + playerId);
    }

    public Image getImage(String playerId) {
        Image image = new Image(Config.skin, "objects/tower_" + playerId);
        //throw new IllegalStateException("no image for " + playerId);
        return image;
    }
}

