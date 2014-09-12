package game.entities;

import TUIO.TuioObject;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import game.Board;
import game.Bullet;
import game.Marker;
import game.Monster;
import game.descriptions.entities.TowerDescription;
import game.descriptions.entities.stats.BulletStats;
import game.descriptions.entities.stats.TowerStats;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Tower extends Marker {

    private final TowerDescription desc;
    private float cooldown = 0;
    public final TowerStats stats;
    public final BulletStats bulletStats;

    public Tower(TuioObject tobj, TowerDescription desc) {
        super(tobj);
        this.desc = desc;
        stats = new TowerStats(desc.towerStats);
        bulletStats = new BulletStats(desc.bulletStats);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        cooldown -= delta;
        if (cooldown <= 0) {
            //can fire
            Monster monster = findMonster();
            if (monster == null) return;
            fireBullet(monster);
        } else {
            //do nothing! lolz
        }
    }

    private void fireBullet(Monster monster) {
        cooldown = stats.speed == 0 ? Float.MAX_VALUE : 1 / stats.speed;
        Bullet bullet = new Bullet(monster, new BulletStats(bulletStats), this);
        bullet.setPosition(getX() + Board.CELL_SIZE / 2, getY() + Board.CELL_SIZE / 2);
        board.addActor(bullet);

    }

    /**
     * @return nearest to castle in radius of attack or null
     */
    @SuppressWarnings("unchecked")
    private Monster findMonster() {
        Array<Monster> monsters = Pools.obtain(Array.class);
        float dst2 = stats.radius * stats.radius * Board.CELL_SIZE * Board.CELL_SIZE;
        for (Monster monster : board.monsters) {
            float dx = monster.getX() - (getX() + Board.CELL_SIZE / 2);
            float dy = monster.getY() - (getY() + Board.CELL_SIZE / 2);
            float monsterDst2 = dx * dx + dy * dy;
            if (monsterDst2 <= dst2) {
                monsters.add(monster);
            }
        }
        if (monsters.size == 0) {
            Pools.free(monsters);
            return null;
        } else if (monsters.size == 1) {
            Monster res = monsters.pop();
            Pools.free(monsters);
            return res;
        } else {
            if (board.castles.size > 0) {
                Castle castle = board.castles.values().toArray().random();
                Monster res = null;
                float minDst2 = Float.MAX_VALUE;
                for (Monster monster : monsters) {
                    float dx = monster.getX() - castle.getX() - Board.CELL_SIZE / 2;
                    float dy = monster.getY() - (castle.getY() + Board.CELL_SIZE / 2);
                    float mDst2 = dx * dx + dy * dy;
                    if (mDst2 < minDst2) {
                        res = monster;
                        minDst2 = mDst2;
                    }
                }
                monsters.clear();
                Pools.free(monsters);
                return res;
            } else {
                Monster res = monsters.random();
                monsters.clear();
                Pools.free(monsters);
                return res;
            }
        }
    }
}
