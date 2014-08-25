package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Sagamor on 11/03/2014.
 */

public abstract class StageScreen implements Screen {

    public final Stage stage = new Stage();
    public final Color bgColor = new Color(Color.WHITE);

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public final void show() {
        Gdx.input.setInputProcessor(stage);
        onShow();
    }

    @Override public final void hide() {
        Gdx.input.setInputProcessor(null);
        onHide();
    }

    @Override public void resize(int width, int height) {
        //stage.setViewport(width, height, true);
    }

    protected abstract void onHide();

    protected abstract void onShow();

    @Override public void pause() {

    }

    @Override public void resume() {
    }

    @Override public void dispose() {}
}
