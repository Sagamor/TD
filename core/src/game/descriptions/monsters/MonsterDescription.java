package game.descriptions.monsters;

/**
 * Created by Sagamor on 11/09/2014.
 */
public class MonsterDescription {
    public float hp;
    public float speed;
    public float damage;

    @Override
    public String toString() {
        return "MonsterDescription{" +
                "hp=" + hp +
                ", speed=" + speed +
                ", damage=" + damage +
                '}';
    }
}
