package game.entities;

import TUIO.TuioObject;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pools;
import game.*;
import game.actors.CircleShapeActor;
import game.descriptions.entities.TowerDescription;
import game.descriptions.entities.stats.BulletStats;
import game.descriptions.entities.stats.TowerStats;
import game.descriptions.entities.upgrades.TowerUpgrade;
import game.ui.ExpBar;
import game.ui.UpgradeView;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Tower extends Marker implements HasExp {

    private final TowerDescription desc;
    private float cooldown = 0;
    public final TowerStats stats;
    public final BulletStats bulletStats;
    private int exp;
    private CircleShapeActor radiusCircle;

    public Tower(TuioObject tobj, TowerDescription desc) {
        super(tobj);
        this.desc = desc;
        stats = new TowerStats(desc.towerStats);
        bulletStats = new BulletStats(desc.bulletStats);
        ExpBar expBar = new ExpBar(this);
        expBar.setWidth(Board.CELL_SIZE);
        addActor(expBar);
        expBar.setPosition(
                Board.CELL_SIZE / 2 - expBar.getWidth() / 2, Board.CELL_SIZE - expBar.getHeight()
        );
        addActor(radiusCircle);
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
        radiusCircle.setSize(desc.towerStats.radius, desc.towerStats.radius);
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

    public void addExp(int exp) {
        int prevLevel = getCurrentLevel();
        this.exp += exp;
        int currentLevel = getCurrentLevel();
        if (currentLevel > prevLevel) {
            //todo
            IntMap<Array<Array<TowerUpgrade>>> towerUpgrades = Config.upgrades.get(tobj.getSymbolID());
            System.out.println("LEVEL UP!");
            if (towerUpgrades != null) {
                Array<Array<TowerUpgrade>> currentLevelUpgrades = towerUpgrades.get(currentLevel);
                if (currentLevelUpgrades != null) {
                    int x = coordinate.x;
                    int y = coordinate.y;
                    Array<UpgradeView> upgradeViews = new Array<UpgradeView>();
                    addUpgrade(x - 1, y, 0, currentLevelUpgrades, upgradeViews);
                    addUpgrade(x + 1, y, 1, currentLevelUpgrades, upgradeViews);
                    addUpgrade(x, y - 1, 2, currentLevelUpgrades, upgradeViews);
                    addUpgrade(x, y + 1, 3, currentLevelUpgrades, upgradeViews);
                }
            }
        }
    }

    private void addUpgrade(int x, int y, int i, Array<Array<TowerUpgrade>> currentLevelUpgrades, Array<UpgradeView> upgradeViews) {
        if (x < 0 || x > board.width || y < 0 || y > board.height)
            return;
        Array<TowerUpgrade> cellUpgrades = currentLevelUpgrades.get(i % currentLevelUpgrades.size);
        UpgradeView view = new UpgradeView(this, x, y, cellUpgrades, upgradeViews);
        upgradeViews.add(view);
        board.addActor(view);
    }

    public int getExp() {
        return exp;
    }

    public int getCurrentLevel() {
        for (int i = desc.expsPerLevel.size - 1; i >= 0; i--) {
            if (exp >= desc.expsPerLevel.get(i))
                return i + 1;
        }
        return 0;
    }

    public int getExpForNextLevel(int level) {
        if (level < 0) return 0;
        if (level >= desc.expsPerLevel.size) return desc.expsPerLevel.peek();
        return desc.expsPerLevel.get(level);

    }

    public float getLevelProgress() {
        int level = getCurrentLevel();
        int expForCurrentLevel = getExpForNextLevel(level - 1);
        int expForNextLevel = getExpForNextLevel(level);
        if (expForCurrentLevel == expForNextLevel)
            return 1f;
        return ((float) (exp - expForCurrentLevel)) / ((float) (expForNextLevel - expForCurrentLevel));
    }
}
