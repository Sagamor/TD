package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ShapeActor extends Actor {

    private static final ShapeRenderer renderer = new ShapeRenderer();

    @Override public void draw(SpriteBatch batch, float parentAlpha) {
        batch.end();
        Color color = getColor();
        renderer.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        Gdx.gl.glEnable(GL10.GL_LINE_SMOOTH);
        Gdx.gl.glEnable(GL10.GL_POINT_SMOOTH);
        Gdx.gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST);
        Gdx.gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_NICEST);

        draw(renderer);
        Gdx.gl20.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    protected abstract void draw(ShapeRenderer renderer);


}
