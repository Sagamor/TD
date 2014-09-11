package game.descriptions;

import com.badlogic.gdx.utils.ObjectMap;
import game.descriptions.waves.WaveParams;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class WaveDescription {
    public ObjectMap<String, WaveParams> monsters;
    public float cooldown;

    @Override
    public String toString() {
        return "WaveDescription{" +
                "monsters=" + monsters +
                ", cooldown=" + cooldown +
                '}';
    }
}
