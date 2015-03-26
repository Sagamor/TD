package game.descriptions.entities.upgrades;

import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class AddUpgrade extends TowerUpgrade {
    @Override
    public void apply(Tower tower) {
        if (towerStats != null) {
            tower.stats.radius += towerStats.radius;
            tower.stats.speed += towerStats.speed;
            System.out.println("tower stats radius = " + tower.stats.radius);
        }
        if (bulletStats != null) {

            tower.bulletStats.speed += bulletStats.speed;
            tower.bulletStats.damage += bulletStats.damage;

            tower.bulletStats.dotDamage += bulletStats.dotDamage;
            tower.bulletStats.dotTime += bulletStats.dotTime;

            tower.bulletStats.slowTime += bulletStats.slowTime;
            tower.bulletStats.slowFactor += bulletStats.slowFactor;

            tower.bulletStats.chainProbability += bulletStats.chainProbability;
            tower.bulletStats.chainProbabilityMultiplier += bulletStats.chainProbabilityMultiplier;
            tower.bulletStats.chainDamageMultiplier += bulletStats.chainDamageMultiplier;

            tower.bulletStats.instantKillProbability += bulletStats.instantKillProbability;
        }
    }
}
