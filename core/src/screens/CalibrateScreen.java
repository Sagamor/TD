package screens;

import TUIO.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import game.Config;
import game.Main;

/**
 * Created by Sagamor on 01/04/2014.
 */
public class CalibrateScreen extends StageScreen implements TuioListener {
    public static final float WIDTH = 9;
    public static final float HEIGHT = 7;

    public static Vector2 bottomLeft = new Vector2(0,0);
    public static Vector2 bottomRight = new Vector2(1,0);;
    public static Vector2 topLeft = new Vector2(0,1);;

    private Main main;

    public CalibrateScreen(Main main) {
        this.main = main;
    }

    @Override public void onShow() {
        final CalibrateScreen calibrateScreen = this;
        final TuioClient client = new TuioClient();
        client.addTuioListener(calibrateScreen);
        client.connect();

        TextButton button = new TextButton("Play", Config.skin);
        button.setSize(50, 20);
        button.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                client.removeTuioListener(calibrateScreen);
                main.setScreen(new GameScreen(client));
            }
        });

        Label label = new Label("Tower Defence Project \n", Config.skin);
        label.setColor(0, 0, 0, 1);

        Table table = new Table();
        table.setFillParent(true);
        table.add(label);
        table.row();
        table.add(button);
        stage.addActor(table);
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
