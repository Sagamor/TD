package game.controllers;

import TUIO.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.ObjectMap;
import game.Board;
import game.Coordinate;
import game.Marker;
import screens.CalibrateScreen;

/**
 * Created by Sagamor on 24/03/2014.
 */
public class MarkerController implements TuioListener {

    public ObjectMap<Marker, Coordinate> playerCoords = new ObjectMap<Marker, Coordinate>();

    Board board;
    IntSet ids = new IntSet();
    {
        ids.add(109);
        ids.add(110);
        ids.add(115);
        ids.add(117);
    }

    public MarkerController(Board board, TuioClient client) {
        this.board = board;
        for(TuioObject obj : client.getTuioObjects()){
            addTuioObject(obj);
        }
    }

    @Override
    public void addTuioObject(final TuioObject tobj) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
            if (ids.contains(tobj.getSymbolID())){
                Marker marker = new Marker(tobj);
                Coordinate coords = coordinateTransform(tobj);
                board.addMarker(tobj, marker, coords);
                board.move(coords.x, coords.y, tobj);
                playerCoords.put(marker, coords);
            }

//                if (tobj.getSymbolID() == 116) {
//                    Coordinate coords = coordinateTransform(tobj);
//                    board.updateMarkerStability(coords.x);
//                }
            }
        });
    }

    @Override
    public void updateTuioObject(final TuioObject tobj) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                Coordinate coords = coordinateTransform(tobj);

                for (ObjectMap.Entry<Marker, Coordinate> entry : playerCoords.entries()) {
                    if (entry.key.tobj.equals(tobj)) {
                        if (!entry.value.equals(coords)) {
                            board.move(coords.x, coords.y, tobj);
                            playerCoords.put(entry.key, coords);
                        }
                    }
                }

//                if (tobj.getSymbolID() == 116) {
//                    board.updateMarkerStability(coords.x);
//                }

            }
        });
    }

    @Override
    public void removeTuioObject(final TuioObject tobj) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                board.removeMarker(tobj);
            }
        });
    }

    public Coordinate coordinateTransform(TuioObject tobj) {
//        float x = 1 - tobj.getX();
//        float y = tobj.getY();
//        x = x * Gdx.graphics.getWidth();
//        y = y * Gdx.graphics.getHeight();
//        Vector2 boardCoordinate = board.screenToLocalCoordinates(new Vector2(x, y));
//        int boardCoordinateX = (int) (boardCoordinate.x / Board.CELL_SIZE);
//        int boardCoordinateY = (int) (boardCoordinate.y / Board.CELL_SIZE);
//        return new Coordinate(boardCoordinateX, boardCoordinateY);

        Vector2 point = new Vector2(1-tobj.getX(), tobj.getY()).scl(1000f);

        Vector2 horizontalDirection = new Vector2(CalibrateScreen.bottomRight).sub(CalibrateScreen.bottomLeft);
        float horizontalAngle = horizontalDirection.angle();

        float zeroX = new Vector2(CalibrateScreen.bottomLeft).rotate(-horizontalAngle).x;
        float maxX = new Vector2(CalibrateScreen.bottomRight).rotate(-horizontalAngle).x;
        float pointX = new Vector2(point).rotate(-horizontalAngle).x;
        maxX -= zeroX;
        pointX -= zeroX;
        pointX = pointX / maxX * CalibrateScreen.WIDTH - 1;

        Vector2 verticalDirection = new Vector2(CalibrateScreen.topLeft).sub(CalibrateScreen.bottomLeft).nor();
        float verticalAngle = verticalDirection.angle();
        float zeroY = new Vector2(CalibrateScreen.bottomLeft).rotate(90 - verticalAngle).y;
        float maxY = new Vector2(CalibrateScreen.topLeft).rotate(90 - verticalAngle).y;
        float pointY = new Vector2(point).rotate(90 - verticalAngle).y;
        maxY -= zeroY;
        pointY -= zeroY;
        pointY = pointY / maxY * CalibrateScreen.HEIGHT;

        float pX = pointX+0.5f;
        float pY = pointY+0.5f;

//        System.out.println(tobj.getSymbolID() + " : " + pX + " : " + pY);
//        System.out.println(tobj.getSymbolID() + " : " + point);
//        System.out.println(tobj.getSymbolID() + " : " + new Coordinate(MathUtils.floor(pointX+0.5f), MathUtils.floor(pointY+0.5f)));

        return new Coordinate(MathUtils.floor(pX), MathUtils.floor(pY));
    }

    @Override
    public void addTuioCursor(TuioCursor tuioCursor) {

    }

    @Override
    public void updateTuioCursor(TuioCursor tuioCursor) {

    }

    @Override
    public void removeTuioCursor(TuioCursor tuioCursor) {

    }

    @Override
    public void refresh(TuioTime tuioTime) {

    }
}
