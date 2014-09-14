package game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ObjectSet;
import game.descriptions.entities.stats.BulletStats;
import game.descriptions.monsters.MonsterDescription;
import game.entities.Castle;
import game.entities.Tower;
import game.ui.HealthBar;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Monster extends Group implements HasHp {

    private final HealthBar bar;
    private Vector2 speed;
    private final ObjectSet<Tower> slowers = new ObjectSet<Tower>();
    private final MonsterDescription desc;
    private Board board;
    private float hp;

    public Monster(MonsterDescription desc, Board board) {
        this.desc = desc;
        this.board = board;
        hp = desc.hp;
        speed = new Vector2(0, desc.speed * Board.CELL_SIZE);
        Image image = new Image(Config.skin, desc.image);
        image.setCenterPosition(0, 0);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        addActor(image);
        bar = new HealthBar(this);
        bar.setSize(Board.CELL_SIZE / 3, 5);
        bar.setPosition(-bar.getWidth() / 2, 25);
        addActor(bar);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Castle nearestCastle = getNearestCastle(board);
        if (nearestCastle == null)
            return;
        float dy = nearestCastle.getY() + Board.CELL_SIZE / 2 - getY();
        float dx = nearestCastle.getX() + Board.CELL_SIZE / 2 - getX();
        float dst = (float) Math.sqrt(dx * dx + dy * dy);
        if (dst < Board.CELL_SIZE / 4) {
            nearestCastle.setHp(nearestCastle.getHp() - desc.damage);
            board.removeMonster(this);
        } else {
            float angle = (float) (Math.atan2(dy, dx) * 180f / Math.PI);
            speed.setAngle(angle);
            moveBy(speed.x * delta, speed.y * delta);
        }
    }

    private Castle getNearestCastle(Board board) {
        Castle res = null;
        float minDistance = Float.MAX_VALUE;
        for (Castle castle : board.castles.values()) {
            float dx = getX() - castle.getX();
            float dy = getY() - castle.getY();
            float distance = dx * dx + dy * dy;
            if (distance < minDistance) {
                res = castle;
                minDistance = distance;
            }
        }
        return res;
    }

    public float getHp() {
        return hp;
    }

    @Override
    public float getTotalHp() {
        return desc.hp;
    }

    public void setHp(float hp, Tower attackingTower) {
        if (this.hp == hp)
            return;
        this.hp = hp;
        if (hp <= 0) {
            board.removeMonster(this);
            attackingTower.addExp(desc.exp);
        }
    }

    public void addDot(final BulletStats damageOverTime, final Tower attackingTower) {
        if (damageOverTime.dotTime <= 0) {
            setHp(getHp() - damageOverTime.dotDamage, attackingTower);
            return;
        }
        final float dps = damageOverTime.dotDamage / damageOverTime.dotTime;
        addAction(new Action() {
            float accumulated = 0;

            @Override
            public boolean act(float delta) {
                setHp(getHp() - delta * dps, attackingTower);
                accumulated += delta;
                return accumulated >= damageOverTime.dotTime;
            }
        });
    }

    public void slow(final BulletStats stats, final Tower tower) {
        if (stats.slowFactor <= 0 || stats.slowTime <= 0 || slowers.contains(tower))
            return;
        slowers.add(tower);
        TemporalAction action = new TemporalAction() {

            @Override
            protected void begin() {
                speed.scl(stats.slowFactor);
            }

            @Override
            protected void end() {
                speed.scl(1 / stats.slowFactor);
                slowers.remove(tower);
            }

            @Override
            protected void update(float percent) {
            }
        };
        action.setDuration(stats.slowTime);
        addAction(action);

    }
}
