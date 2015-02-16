package game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import game.descriptions.Point;
import game.descriptions.WaveDescription;

/**
 * Created by Sagamor on 01/02/15.
 */
public class GameSettings {

    public IntMap<Point> positions;
    public Array<WaveDescription> waveDescriptions;

    public GameSettings(IntMap<Point> positions, Array<WaveDescription> waveDescriptions) {
        this.positions = positions;
        this.waveDescriptions = waveDescriptions;
    }
    @Override public String toString() {
        return "GameSettings{" +
            "positions=" + positions +
            ", waveDescriptions=" + waveDescriptions +
            '}';
    }
}
