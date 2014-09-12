package game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import game.descriptions.entities.stats.BulletStats;
import game.entities.Tower;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Bullet extends Group {

    private Vector2 speed;
    private final Monster monster;
    private final BulletStats stats;
    private final Tower tower;

    public Bullet(Monster monster, BulletStats stats, Tower tower) {
        this.monster = monster;
        this.stats = stats;
        this.tower = tower;
        speed = new Vector2(0, stats.speed * Board.CELL_SIZE);
        Image image = new Image(Config.skin, "objects/bullet");
        image.setCenterPosition(0, 0);
        addActor(image);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (monster.getParent() == null) {
            remove();
            return;
        }
        float dy = monster.getY() - getY();
        float dx = monster.getX() - getX();
        float dst = (float) Math.sqrt(dx * dx + dy * dy);
        if (dst < Board.CELL_SIZE / 8) {
            hitMonster();
            remove();
        } else {
            float angle = (float) (Math.atan2(dy, dx) * 180f / Math.PI);
            speed.setAngle(angle);
            moveBy(speed.x * delta, speed.y * delta);
        }
    }

    private void hitMonster() {
        if (MathUtils.randomBoolean(stats.instantKillProbability)) {
            monster.setHp(0);
            Label label = new Label("Instant Kill!", Config.skin);
            label.setPosition(getX() - label.getWidth() / 2, getY() - label.getHeight() / 2);
            label.addAction(Actions.sequence(
                    Actions.alpha(0, 1f),
                    Actions.removeActor()
            ));
            getParent().addActor(label);
            return;
        }
        monster.setHp(monster.getHp() - stats.damage);
        monster.addDot(stats);
        monster.slow(stats, tower);
        if (MathUtils.randomBoolean(stats.chainProbability)) {
            Monster nearest = null;
            float minDst = Float.MAX_VALUE;
            for (Monster monster : tower.board.monsters) {
                if (monster == this.monster)
                    continue;
                float dx = getX() - monster.getX();
                float dy = getY() - monster.getY();
                float dst2 = dx * dx + dy * dy;
                if (dst2 < minDst) {
                    nearest = monster;
                    minDst = dst2;
                }
            }
            if (nearest != null) {
                BulletStats newStats = new BulletStats(stats);
                newStats.chainProbability = stats.chainProbability * stats.chainProbabilityMultiplier;
                newStats.damage = stats.damage * stats.chainDamageMultiplier;
                Bullet bullet = new Bullet(nearest, newStats, tower);
                bullet.setPosition(getX(), getY());
                getParent().addActor(bullet);
            }
        }
    }
}
