package screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.Config;

/**
 * Created by Sagamor on 12/09/2014.
 */
public class LoseScreen extends StageScreen {
    @Override
    protected void onShow() {
        TextButton playAgain = new TextButton("Defeat!ukhotin9aa0 Play again?", Config.skin);
        Container<TextButton> container = new Container<TextButton>(playAgain);
        container.setFillParent(true);
        stage.addActor(container);
        playAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Config.app.setScreen(new GameScreen(Config.tuio));
            }
        });
    }

    @Override
    protected void onHide() {

    }
}
