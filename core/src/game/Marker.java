package game;

import TUIO.TuioObject;
import game.actors.CircleShapeActor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.IntMap;

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

    public Marker(TuioObject tobj) {
        this.tobj = tobj;

        CircleShapeActor chip = new CircleShapeActor();
        //chip.setPosition(Board.CELL_SIZE / 2f, Board.CELL_SIZE / 2f);
        chip.setSize(Board.CELL_SIZE / 3f, Board.CELL_SIZE / 3f);
        chip.setPosition(Board.CELL_SIZE / 2f - chip.getWidth() / 2f, Board.CELL_SIZE / 2f - chip.getHeight() / 2f);
        chip.setColor(getColor(idToFileName.get(tobj.getSymbolID())));
        addActor(chip);

        final CircleShapeActor circle1 = createCircle();
        final CircleShapeActor circle2 = createCircle();
        final CircleShapeActor circle3 = createCircle();

        circle1.addAction(Actions.sequence(
                Actions.delay(0),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle1);
                    }
                })
        ));
        circle2.addAction(Actions.sequence(
                Actions.delay(0.7f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle2);
                    }
                })
        ));
        circle3.addAction(Actions.sequence(
                Actions.delay(1.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle3);
                    }
                })
        ));
    }

    public Marker() {
        CircleShapeActor chip = new CircleShapeActor();
        //chip.setPosition(Board.CELL_SIZE * 2, Board.CELL_SIZE * 2);
        chip.setSize(Board.CELL_SIZE / 3f, Board.CELL_SIZE / 3f);
        chip.setColor(getColor(idToFileName.get(tobj.getSymbolID())));
        addActor(chip);

        final CircleShapeActor circle1 = createCircle();
        final CircleShapeActor circle2 = createCircle();
        final CircleShapeActor circle3 = createCircle();

        circle1.addAction(Actions.sequence(
                Actions.delay(0),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle1);
                    }
                })
        ));
        circle2.addAction(Actions.sequence(
                Actions.delay(0.7f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle2);
                    }
                })
        ));
        circle3.addAction(Actions.sequence(
                Actions.delay(1.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        animate(circle3);
                    }
                })
        ));
    }

    public CircleShapeActor createCircle() {
        CircleShapeActor circleNew = new CircleShapeActor();
        circleNew.setSize(Board.CELL_SIZE / 3f, Board.CELL_SIZE / 3f);
        circleNew.setPosition(Board.CELL_SIZE / 2f - circleNew.getWidth() / 2f, Board.CELL_SIZE / 2f - circleNew.getHeight() / 2f);
        circleNew.setColor(0,0,0,0);
        circleNew.setColor(getColor(idToFileName.get(tobj.getSymbolID())));
        addActor(circleNew);
        return circleNew;
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
}

