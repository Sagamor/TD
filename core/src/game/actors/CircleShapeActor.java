package game.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CircleShapeActor extends ShapeActor {


    @Override protected void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.ellipse(getX(), getY(), getWidth(), getHeight());
        renderer.end();
    }

}
