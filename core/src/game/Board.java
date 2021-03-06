package game;

import TUIO.TuioObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
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
    public Array<Monster> monsters = new Array<Monster>();

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
        Coordinate coordinate = new Coordinate(x, y);
        Cell cell = data.get(coordinate);
        Marker marker = markers.get(tobj);
        marker.coordinate = coordinate;
        marker.setPosition(x * CELL_SIZE, y * CELL_SIZE);
        if (cell != null) {
            cell.occupy(this);
//            ui.showOpenedTile(cell);
        }
        fire(new ChangeListener.ChangeEvent());
    }

    public void addMarker(TuioObject tobj, Marker marker, Coordinate coords) {
        if (marker instanceof Tower) {
            towers.put(tobj, ((Tower) marker));
        }
        if (marker instanceof Castle) {
            castles.put(tobj, ((Castle) marker));
        }
        marker.board = this;
        marker.coordinate = coords;
        markers.put(tobj, marker);
        marker.setPosition(coords.x * CELL_SIZE, coords.y * CELL_SIZE);
        playerLayer.addActor(marker);
        fire(new ChangeListener.ChangeEvent());
    }

    public void removeMarker(TuioObject tobj) {
        Marker marker = markers.remove(tobj);
        if (marker != null) {
            marker.remove();
            if (marker instanceof Castle) {
                castles.remove(tobj);
            }
            if (marker instanceof Tower) {
                towers.remove(tobj);
            }
        }
        fire(new ChangeListener.ChangeEvent());
    }

    public void addMonster(Monster monster) {
        addActor(monster);
        float cx = width / 2 * CELL_SIZE;
        float cy = height / 2 * CELL_SIZE;
        float distance = (width + 2) * CELL_SIZE;
        float angle = MathUtils.random(0, 360);
        Vector2 pos = new Vector2(0, distance).rotate(angle).add(cx, cy);
        monster.setPosition(pos.x, pos.y);
        monsters.add(monster);
        fire(new ChangeListener.ChangeEvent());
    }

    public void removeMonster(Monster monster) {
        monster.remove();
        monsters.removeValue(monster, true);
        fire(new ChangeListener.ChangeEvent());
    }
}
