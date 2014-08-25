package screens;

import TUIO.TuioClient;
import game.Board;
import game.controllers.MarkerController;
import game.controllers.UIController;

/**
 * Created by Sagamor on 11/03/2014.
 */
public class GameScreen extends StageScreen {

    private final TuioClient client;

    public GameScreen(TuioClient client) {
        this.client = client;
    }

    @Override public void onShow() {

//        BoardDescription boardDescription = new Json().fromJson(BoardDescription.class, Gdx.files.internal("board.json"));
//        PlanetDescription planetDescription = new Json().fromJson(PlanetDescription.class, Gdx.files.internal("cards.json"));
//        GameEventsDescription eventsDescription = new Json().fromJson(GameEventsDescription.class, Gdx.files.internal("events.json"));

//        System.out.println(planetDescription);
//        System.out.println(boardDescription);
//        System.out.println(eventsDescription);

        Board board = new Board();
        board.bgColor = bgColor;

        UIController uiController = new UIController(stage, board);
//        GameMaster gameMaster = new GameMaster(stage, board, eventsDescription);
        MarkerController markers = new MarkerController(board, client);

        client.addTuioListener(markers);
    }

    @Override public void onHide() {

    }
}
