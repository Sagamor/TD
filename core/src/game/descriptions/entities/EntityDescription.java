package game.descriptions.entities;

import TUIO.TuioObject;
import game.GameSettings;
import game.Marker;

/**
 * Created by Sagamor on 10/09/2014.
 */
public abstract class EntityDescription {
    public abstract Marker createEntity(TuioObject tobj, GameSettings settings);
}
