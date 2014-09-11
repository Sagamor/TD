package game.descriptions.entities;

import TUIO.TuioObject;
import com.badlogic.gdx.utils.IntArray;
import game.Marker;
import game.descriptions.entities.stats.BulletStats;
import game.descriptions.entities.stats.TowerStats;
import game.entities.Tower;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class TowerDescription extends EntityDescription {
    public int hp;
    public IntArray expsPerLevel;
    public TowerStats towerStats;
    public BulletStats bulletStats;

    @Override
    public String toString() {
        return "TowerDescription{" +
                "hp=" + hp +
                ", expsPerLevel=" + expsPerLevel +
                ", towerStats=" + towerStats +
                ", bulletStats=" + bulletStats +
                '}';
    }

    @Override
    public Marker createEntity(TuioObject tobj) {
        return new Tower(tobj, this);
    }
}
