package game;

import TUIO.TuioObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectMap;
import game.actors.BoardView;
import game.controllers.UIController;

public class Board extends Group {

    public static final int CELL_SIZE = Gdx.graphics.getHeight() / 8;

    public final int width;
    public final int height;

    public final ObjectMap<Coordinate, Cell> data = new ObjectMap<Coordinate, Cell>();
    public ObjectMap<TuioObject, Marker> markers = new ObjectMap<TuioObject, Marker>();

    public Color bgColor;

    public UIController ui;

    public final Group effectLayer = new Group();
    public final Group fieldLayer = new Group();
    public final Group playerLayer = new Group();

    public int stabilityMarker = -1;

    public Board() {
        width = 8;
        height = 8;


        setSize(width * CELL_SIZE, height * CELL_SIZE);
        //data.get(new Coordinate(1,1));
        addActor(new BoardView(this));

        addActor(fieldLayer);
        addActor(playerLayer);
        addActor(effectLayer);
    }

    //    private void add(final Coordinate coordinate, final Field field) {
//        data.put(coordinate, field);
//        field.setPosition(coordinate.x * CELL_SIZE, coordinate.y * CELL_SIZE);
//        field.addListener(new ActorGestureListener() {
//            @Override
//            public void tap(InputEvent event, float x, float y, int count, int button) {
//                field.visit(Board.this);
//                ui.showOpenedTile(field);
//            }
//        });
//        fieldLayer.addActor(field);
//    }

    public void move(int x, int y, TuioObject tobj) {
        //System.out.println(tobj.getSymbolID() + " @ " + x + " : " + y);
        Cell cell = data.get(new Coordinate(x, y));
        Marker marker = markers.get(tobj);
        marker.setPosition(x*CELL_SIZE, y*CELL_SIZE);
        if (cell != null) {
            cell.occupy(this);
//            ui.showOpenedTile(cell);
        }
    }
//
//    public void hideCard(int x, int y) {
//        Terrain terrain = boardDesc.get(x, y);
//        Cell removedCell = data.remove(new Coordinate(x, y));
//        removedCell.remove();
//        fieldsDeck.returnField(removedCell);
//        this.add(new Coordinate(x, y), fieldsDeck.pickRandomCard(terrain));
//        fieldsDeck.cards.shuffle();
//    }
//
//
//
//    public Cell getCardAt(int x, int y) {
//        return data.get(new Coordinate(x, y));
//    }
//
    public void addMarker(TuioObject tobj, Marker marker, Coordinate coords) {
        markers.put(tobj, marker);
        marker.setPosition(coords.x*CELL_SIZE, coords.y*CELL_SIZE);
        playerLayer.addActor(marker);
    }

    public void removeMarker(TuioObject tobj) {
        Marker marker = markers.remove(tobj);
        if (marker != null)
            marker.remove();
    }
//
//    public void updateMarkerStability(int i) {
//        stabilityMarker = i;
//        ui.stabilityUpdate();
//    }
//
//    public int getStability() {
//        return stability;
//    }
//
//    public int setStability(int i) {
//        stability = i;
//        if (stability >=8 )
//            stability = 8;
//        else if (stability <= 0)
//            stability = 0;
//        ui.stabilityUpdate();
//        return stability;
//    }
//
//    public int getStabilityMarker() {
//        return stabilityMarker;
//    }
}
