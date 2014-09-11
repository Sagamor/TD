package game.descriptions.entities.stats;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class TowerStats {
    @Override
    public String toString() {
        return "TowerStats{" +
                "speed=" + speed +
                '}';
    }

    public float speed;
    public float radius = 1.5f;
}
