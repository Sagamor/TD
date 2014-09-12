package game.descriptions.entities.stats;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class TowerStats {
    public TowerStats() {
    }

    public TowerStats(TowerStats stats) {
        speed = stats.speed;
        radius = stats.radius;
    }

    @Override
    public String toString() {
        return "TowerStats{" +
                "speed=" + speed +
                '}';
    }

    public float speed;
    public float radius = 1.5f;
}
