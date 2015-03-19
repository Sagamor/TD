package screens;

import TUIO.TuioClient;
import game.Board;
import game.Checker;
import game.GameSettings;
import game.controllers.MarkerController;

/**
 * Created by Sagamor on 11/03/2014.
 */
public class GameScreen extends StageScreen {

    private final TuioClient client;
    private final GameSettings settings;

    public GameScreen(TuioClient client, GameSettings settings) {
        this.client = client;
        this.settings = settings;
    }

    @Override public void onShow() {

        Board board = new Board();
        board.bgColor = bgColor;
        new Checker(board, settings);
        MarkerController markers = new MarkerController(board, client, settings);

        client.addTuioListener(markers);
    }

    @Override public void onHide() {

    }
}
