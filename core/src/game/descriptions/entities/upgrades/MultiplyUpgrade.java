package game.descriptions.entities.upgrades;

import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class MultiplyUpgrade extends TowerUpgrade {
    @Override
    public void apply(Tower tower) {
        if (towerStats != null) {
            if (towerStats.radius != 0) tower.stats.radius *= towerStats.radius;
            if (towerStats.speed != 0) tower.stats.speed *= towerStats.speed;
        }
        if (bulletStats != null) {

            if (bulletStats.speed != 0) tower.bulletStats.speed *= bulletStats.speed;
            if (bulletStats.damage != 0) tower.bulletStats.damage *= bulletStats.damage;

            if (bulletStats.dotDamage != 0) tower.bulletStats.dotDamage *= bulletStats.dotDamage;
            if (bulletStats.dotTime != 0) tower.bulletStats.dotTime *= bulletStats.dotTime;

            if (bulletStats.slowTime != 0) tower.bulletStats.slowTime *= bulletStats.slowTime;
            if (bulletStats.slowFactor != 0) tower.bulletStats.slowFactor *= bulletStats.slowFactor;

            if (bulletStats.chainProbability != 0)
                tower.bulletStats.chainProbability *= bulletStats.chainProbability;
            if (bulletStats.chainProbabilityMultiplier != 0)
                tower.bulletStats.chainProbabilityMultiplier *= bulletStats.chainProbabilityMultiplier;
            if (bulletStats.chainDamageMultiplier != 0)
                tower.bulletStats.chainDamageMultiplier *= bulletStats.chainDamageMultiplier;

            if (bulletStats.instantKillProbability != 0)
                tower.bulletStats.instantKillProbability *= bulletStats.instantKillProbability;
        }
    }
}
