package game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.HasHp;
import game.actors.ShapeActor;

/**
 * Created by Sagamor on 12/09/2014.
 */
public class HealthBar extends ShapeActor {

    private final HasHp hasHp;

    public HealthBar(HasHp hasHp) {
        this.hasHp = hasHp;
    }

    @Override
    protected void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.setColor(Color.RED);
        float width = getWidth() * (1 - hasHp.getHp() / hasHp.getTotalHp());
        renderer.rect(getX() + getWidth() - width, getY(), width, getHeight());
        renderer.end();
    }
}
