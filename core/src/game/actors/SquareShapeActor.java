package game.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SquareShapeActor extends ShapeActor {


    @Override protected void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.end();
    }

}
