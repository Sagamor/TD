package game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import game.actors.BoardView;
import game.controllers.WaveController;
import game.descriptions.Point;

/**
 * Created by Администратор on 05.03.2015.
 */
public class Checker {

    private final ChangeListener listener;
    private Board board;
    private GameSettings settings;
    private Array<Image> images = new Array<Image>();

    public Checker(final Board board, GameSettings settings) {
        this.board = board;
        this.settings = settings;

        for (IntMap.Entry<Point> entry : settings.positions) {
            int id = entry.key;
            Point position = entry.value;
            Image image = Marker.createImage(id);
            images.add(image);
            image.moveBy(position.x * Board.CELL_SIZE, position.y * Board.CELL_SIZE);
            image.getColor().a = 0.25f;
            board.addActor(image);
        }

        listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (canStart())
                    Start();
            }
        };
        board.addListener(listener);
    }

    private void Start() {
        board.removeListener(listener);
        for (Image image : images) {
            image.remove();
        }
        new WaveController(board, settings);
    }

    private boolean canStart() {
        IntMap<Marker> markers = new IntMap<Marker>();
        for (Marker marker : board.markers.values()) {
            markers.put(marker.getId(), marker);
        }
        for (IntMap.Entry<Point> entry : settings.positions) {
            int id = entry.key;
            Marker marker = markers.get(id);
            if (marker == null)
                return false;
            if (marker.coordinate.x != entry.value.x || marker.coordinate.y != entry.value.y)
                return false;
        }
        return true;
    }

}
