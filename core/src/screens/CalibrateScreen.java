package screens;

import TUIO.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.IntMap;
import game.Board;
import game.Config;
import game.GameSettings;
import game.Marker;
import game.actors.BoardView;
import game.descriptions.Point;

/**
 * Created by Sagamor on 01/04/2014.
 */
public class CalibrateScreen extends StageScreen implements TuioListener {
    public static final float WIDTH = 9;
    public static final float HEIGHT = 7;

    public static Vector2 bottomLeft = new Vector2(0,0);
    public static Vector2 bottomRight = new Vector2(1,0);
    public static Vector2 topLeft = new Vector2(0,1);

    private Game main;
    private TuioClient client;

    public CalibrateScreen(Game main) {
        this.main = main;
    }

    @Override public void onShow() {
        final CalibrateScreen calibrateScreen = this;
        client = new TuioClient();
        client.addTuioListener(calibrateScreen);
        client.connect();
        Config.tuio = client;

        Label label = new Label("Tower Defence Project \n", Config.skin);
        label.setColor(0, 0, 0, 1);

        Table table = new Table();
        table.setFillParent(true);
        table.add(label);
        table.row();
        Table items = new Table();
        items.defaults().pad(10);
        for (GameSettings settings : Config.gameSettings.values()) {
            Actor preview = createView(settings);
            preview.addListener(createStartListener(settings));
            items.add(preview);
        }
        table.add(items);
        stage.addActor(table);
    }
    private EventListener createStartListener(final GameSettings settings) {
        return new ActorGestureListener() {
            @Override public void tap(InputEvent event, float x, float y, int count, int button) {
                client.removeTuioListener(CalibrateScreen.this);
                main.setScreen(new GameScreen(client, settings));
            }
        };
    }

    private Actor createView(GameSettings settings) {
        Group wrapper = new Group();
        wrapper.setTouchable(Touchable.disabled);
        wrapper.addActor(new BoardView(8, 8));
        for (IntMap.Entry<Point> entry : settings.positions) {
            int id = entry.key;
            Point position = entry.value;
            Image image = Marker.createImage(id);
            image.moveBy(position.x * Board.CELL_SIZE, position.y * Board.CELL_SIZE);
            wrapper.addActor(image);
        }
        wrapper.setScale(0.5f);

        Group result = new Group();
        result.addActor(wrapper);
        result.setTouchable(Touchable.enabled);
        result.setSize(8 * Board.CELL_SIZE * wrapper.getScaleX(), 8 * Board.CELL_SIZE * wrapper.getScaleY());
        return result;
    }

    @Override
    protected void onHide() {

    }

    private void update(TuioObject tobj) {
        switch (tobj.getSymbolID()) {
            case 110:
                bottomLeft = new Vector2(1-tobj.getX(), tobj.getY()).scl(1000f);
                System.out.println("bottomLeft: " + bottomLeft);
                break;
            case 117:
                bottomRight = new Vector2(1-tobj.getX(), tobj.getY()).scl(1000f);
                System.out.println("bottomRight: " + bottomRight);
                break;
            case 109:
                topLeft = new Vector2(1-tobj.getX(), tobj.getY()).scl(1000f);
                System.out.println("topLeft: " + topLeft);
                break;
        }
    }

    @Override
    public void addTuioObject(final TuioObject tobj) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                update(tobj);
            }
        });
    }

    @Override
    public void updateTuioObject(final TuioObject tobj) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                update(tobj);
            }
        });
    }

    @Override
    public void removeTuioObject(TuioObject tobj) {

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
