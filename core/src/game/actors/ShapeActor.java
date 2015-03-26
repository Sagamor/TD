package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ShapeActor extends Actor {

    private static final ShapeRenderer renderer = new ShapeRenderer();

    private float lineWidth = 1f;

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        Color color = getColor();
        renderer.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl20.glLineWidth(lineWidth);

        draw(renderer);
        Gdx.gl20.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    protected abstract void draw(ShapeRenderer renderer);


}
