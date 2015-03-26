package game.descriptions.entities.upgrades;

import game.descriptions.entities.stats.BulletStats;
import game.descriptions.entities.stats.TowerStats;
import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public abstract class TowerUpgrade {
    TowerStats towerStats;
    BulletStats bulletStats;

    public abstract void apply(Tower tower);
}
