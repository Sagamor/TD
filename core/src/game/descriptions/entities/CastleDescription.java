package game.descriptions.entities;

import TUIO.TuioObject;
import game.GameSettings;
import game.Marker;
import game.entities.Castle;

/**
 * Created by Sagamor on 10/09/2014.
 */
public class CastleDescription extends EntityDescription {
    public int hp;

    @Override
    public String toString() {
        return "CastleDescription{" +
                "hp=" + hp +
                '}';
    }


    @Override
    public Marker createEntity(TuioObject tobj, GameSettings settings) {
        return new Castle(tobj, this, settings);
    }
}
