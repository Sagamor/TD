package game;

import TUIO.TuioObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectMap;
import game.actors.BoardView;
import game.controllers.UIController;
import game.entities.Castle;
import game.entities.Tower;

public class Board extends Group {
    public static final int CELL_SIZE = Gdx.graphics.getHeight() / 8;

    public final int width;
    public final int height;

    public final ObjectMap<Coordinate, Cell> data = new ObjectMap<Coordinate, Cell>();
    public ObjectMap<TuioObject, Tower> towers = new ObjectMap<TuioObject, Tower>();
    public ObjectMap<TuioObject, Castle> castles = new ObjectMap<TuioObject, Castle>();
    public ObjectMap<TuioObject, Marker> markers = new ObjectMap<TuioObject, Marker>();

    public Color bgColor;

    public UIController ui;

    public final Group effectLayer = new Group();
    public final Group fieldLayer = new Group();
    public final Group playerLayer = new Group();

    public Board() {
        width = 8;
        height = 8;


        setSize(width * CELL_SIZE, height * CELL_SIZE);
        addActor(new BoardView(this));

        addActor(fieldLayer);
        addActor(playerLayer);
        addActor(effectLayer);
    }


    public void move(int x, int y, TuioObject tobj) {
        //System.out.println(tobj.getSymbolID() + " @ " + x + " : " + y);
        Cell cell = data.get(new Coordinate(x, y));
        Marker marker = markers.get(tobj);
        marker.setPosition(x * CELL_SIZE, y * CELL_SIZE);
        if (cell != null) {
            cell.occupy(this);
//            ui.showOpenedTile(cell);
        }
    }

    public void addMarker(TuioObject tobj, Marker marker, Coordinate coords) {
        if (marker instanceof Tower) {
            towers.put(tobj, ((Tower) marker));
        }
        if (marker instanceof Castle) {
            castles.put(tobj, ((Castle) marker));
        }

        markers.put(tobj, marker);
        marker.setPosition(coords.x * CELL_SIZE, coords.y * CELL_SIZE);
        playerLayer.addActor(marker);
    }

    public void removeMarker(TuioObject tobj) {
        Marker marker = markers.remove(tobj);
        if (marker != null)
            marker.remove();
    }
}
