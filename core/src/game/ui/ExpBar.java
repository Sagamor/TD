package game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.Config;
import game.HasExp;
import game.actors.ShapeActor;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class ExpBar extends Table {
    public ExpBar(final HasExp hasExp) {
        Label label = new Label(String.valueOf(hasExp.getCurrentLevel()), Config.skin) {
            @Override
            public void act(float delta) {
                super.act(delta);
                setText(String.valueOf(hasExp.getCurrentLevel()));
            }
        };

        ShapeActor stripe = new ShapeActor() {
            @Override
            protected void draw(ShapeRenderer renderer) {
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(Color.GRAY);
                renderer.rect(getX(), getY(), getWidth(), getHeight());
                renderer.setColor(Color.YELLOW);
                renderer.rect(getX(), getY(), getWidth() * hasExp.getLevelProgress(), getHeight());
                renderer.end();
            }
        };

        add(label).row();
        add(stripe).expandX().fillX().height(5);
    }
}
