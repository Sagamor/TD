package game.descriptions.entities.upgrades;

import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class MultiplyUpgrade extends TowerUpgrade {
    @Override
    public void apply(Tower tower) {
        if (towerStats != null) {
            if (tower.stats.radius != 0) tower.stats.radius *= towerStats.radius;
            if (tower.stats.speed != 0) tower.stats.speed *= towerStats.speed;
        }
        if (bulletStats != null) {

            if (tower.bulletStats.speed != 0) tower.bulletStats.speed *= bulletStats.speed;
            if (tower.bulletStats.damage != 0) tower.bulletStats.damage *= bulletStats.damage;

            if (tower.bulletStats.dotDamage != 0) tower.bulletStats.dotDamage *= bulletStats.dotDamage;
            if (tower.bulletStats.dotTime != 0) tower.bulletStats.dotTime *= bulletStats.dotTime;

            if (tower.bulletStats.slowTime != 0) tower.bulletStats.slowTime *= bulletStats.slowTime;
            if (tower.bulletStats.slowFactor != 0) tower.bulletStats.slowFactor *= bulletStats.slowFactor;

            if (tower.bulletStats.chainProbability != 0)
                tower.bulletStats.chainProbability *= bulletStats.chainProbability;
            if (tower.bulletStats.chainProbabilityMultiplier != 0)
                tower.bulletStats.chainProbabilityMultiplier *= bulletStats.chainProbabilityMultiplier;
            if (tower.bulletStats.chainDamageMultiplier != 0)
                tower.bulletStats.chainDamageMultiplier *= bulletStats.chainDamageMultiplier;

            if (tower.bulletStats.instantKillProbability != 0)
                tower.bulletStats.instantKillProbability *= bulletStats.instantKillProbability;
        }
    }
}
