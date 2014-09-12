package game.descriptions.entities.stats;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class BulletStats {
    public float speed;
    public float damage;

    public float dotDamage;
    public float dotTime;

    public float slowTime;
    public float slowFactor;

    public float chainProbability;
    public float chainProbabilityMultiplier;
    public float chainDamageMultiplier = 1;

    public float instantKillProbability;

    public BulletStats() {}

    public BulletStats(BulletStats stats) {
        speed = stats.speed;
        damage = stats.damage;

        dotDamage = stats.dotDamage;
        dotTime = stats.dotTime;

        slowTime = stats.slowTime;
        slowFactor = stats.slowFactor;

        chainProbability = stats.chainProbability;
        chainProbabilityMultiplier = stats.chainProbabilityMultiplier;
        chainDamageMultiplier = stats.chainDamageMultiplier;

        instantKillProbability = stats.instantKillProbability;
    }

    @Override
    public String toString() {
        return "BulletStats{" +
                "speed=" + speed +
                ", damage=" + damage +
                ", dotDamage=" + dotDamage +
                ", dotTime=" + dotTime +
                ", slowTime=" + slowTime +
                ", slowFactor=" + slowFactor +
                ", chainProbability=" + chainProbability +
                ", chainProbabilityMultiplier=" + chainProbabilityMultiplier +
                ", chainDamageMultiplier=" + chainDamageMultiplier +
                ", instantKillProbability=" + instantKillProbability +
                '}';
    }
}
