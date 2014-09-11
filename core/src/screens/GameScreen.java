package screens;

import TUIO.TuioClient;
import game.Board;
import game.controllers.MarkerController;
import game.controllers.UIController;
import game.controllers.WaveController;

/**
 * Created by Sagamor on 11/03/2014.
 */
public class GameScreen extends StageScreen {

    private final TuioClient client;

    public GameScreen(TuioClient client) {
        this.client = client;
    }

    @Override public void onShow() {

        Board board = new Board();
        board.bgColor = bgColor;
        UIController ui = new UIController(stage, board);
        new WaveController(board);
        MarkerController markers = new MarkerController(board, client);

        client.addTuioListener(markers);
    }

    @Override public void onHide() {

    }
}
