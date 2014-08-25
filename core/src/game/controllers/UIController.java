package game.controllers;

import game.Marker;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import game.Board;
import game.Config;
import game.Cell;

/**
 * Created by Sagamor on 08/04/2014.
 */
public class UIController {
    Stage stage;
    Board board;

    Table wrapperTable = new Table();
    Table infoTable = new Table();
    Table openFieldInfo = new Table();
    Table openedField = new Table();
    Table playersPlaces = new Table();
    Table stabilityValue = new Table();

    Label stabilityLabel = new Label("Stability", Config.skin, "gray");
    Label weatherLabel = new Label("Weather", Config.skin, "gray");
    Label eventLabel = new Label("Event", Config.skin, "gray");
    Label weatherValue = new Label("- weather -", Config.skin);
    Label eventValue = new Label("- event -", Config.skin);;
    Label terrainLabel = new Label("Terrain", Config.skin, "gray");
    Label fieldLabel = new Label("Field", Config.skin, "gray");
    Label actionLabel = new Label("Action", Config.skin, "gray");
    Label fieldTerrain = new Label("- terrain -", Config.skin);
    Label fieldPlace = new Label("- place -", Config.skin);

    //ObjectMap<String, Image> playerPlace = new ObjectMap<String, Image>();
    Image fieldImg = new Image();

    public UIController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
        board.ui = this;

//        eventValue.setFontScale(1.2f);
//        weatherValue.setFontScale(1.2f);

        eventValue.setText("No event yet");
        weatherValue.setText("No weather yet");


        //infoTable.setFillParent(true);
        infoTable.defaults().align(Align.left).pad(3);
        infoTable.add(stabilityLabel).row();
        infoTable.add(stabilityValue).row();
        infoTable.add(weatherLabel).row();
        infoTable.add(weatherValue).row();
        infoTable.add(eventLabel).row();
        infoTable.add(eventValue).row();
        infoTable.add(openedField).row();
        infoTable.add(playersPlaces);

        wrapperTable.setTransform(true);
        wrapperTable.setFillParent(true);
        board.setScale(0.92f);
        board.setSize(board.getWidth() * board.getScaleX(), board.getHeight() * board.getScaleY());
        stage.addActor(board);
        wrapperTable.add(board).expand(2,1).align(Align.center);
        wrapperTable.add(infoTable).width(1920 / 3f).center().left();

        stage.addActor(wrapperTable);
//        stabilityUpdate();
    }

    public void showOpenedTile(Cell cell) {
        openFieldInfo.clear();
        openedField.clear();
        playersPlaces.clear();

        //fieldImg.setDrawable();
        fieldTerrain.setText("Terrain");
        fieldPlace.setText("Place");


        openFieldInfo.defaults().align(Align.left).pad(5);
        openFieldInfo.add(terrainLabel);
        openFieldInfo.add(fieldTerrain).row();
        openFieldInfo.add(fieldLabel);
        openFieldInfo.add(fieldPlace).row();
        openFieldInfo.add(actionLabel);

        openedField.add(fieldImg).width(130).height(130);
        openedField.add(openFieldInfo);

        int i = 0;

        for(Marker p : board.markers.values()){
            int x = (int) (p.getX()/ Board.CELL_SIZE);
            int y = (int) (p.getY()/ Board.CELL_SIZE);
            int n = i + 1;
            Label playerLabel = new Label("PLAYER " + n, Config.skin);
            Image playerValue = new Image();
            playersPlaces.add(playerLabel);

//            Cell f = board.getCardAt(x, y);
//            if(f != null){
//                playerValue.setDrawable(Config.skin.getDrawable(f.description.terrain + "/" + f.description.place));
//                playerValue.setSize(board.CELL_SIZE / 2f, board.CELL_SIZE / 2f);
//                playersPlaces.add(playerValue).width(100).height(100);
//            }

            if (i == 1)
                playersPlaces.row();
            i++;
            if (i == 4)
                i = 0;
        }

    }

    public void eventUpdate() {

    }

//    public void stabilityUpdate() {
//        stabilityValue.clear();
//
//        stabilityValue.defaults().pad(2);
//
//        for (int i = 0; i < 9; i++) {
//            SquareShapeActor square = new SquareShapeActor();
//            square.setSize(45, 45);
//            square.setColor(Color.valueOf("ececec"));
//            if (i == board.getStability())
//                square.setColor(1-i/9f,i/9f,0,1);
//            else if (i == board.getStabilityMarker())
//                square.setColor(Color.valueOf("999999"));
//            stabilityValue.add(square);
//        }
//        stabilityValue.row();
//        for (int i = 0; i < 9; i++) {
//            Label num = new Label(""+i, Config.skin, "gray");
//            num.setFontScale(0.5f);
//            stabilityValue.add(num).align(Align.center);
//        }
//    }
}
