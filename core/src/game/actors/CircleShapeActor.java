package game.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CircleShapeActor extends ShapeActor {

    private ShapeRenderer.ShapeType shapeType = ShapeRenderer.ShapeType.Filled;

    public void setShapeType(ShapeRenderer.ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override protected void draw(ShapeRenderer renderer) {
        renderer.begin(shapeType);
        renderer.ellipse(getX(), getY(), getWidth(), getHeight());
        renderer.end();
    }

}
