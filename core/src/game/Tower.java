package game;

import TUIO.TuioObject;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Tower extends Marker {

    public int hp;
    public int exp;
    public int level;
    public int shootingSpeed;
    public TuioObject tobj;
    public Coordinate coords;

    public int bulletSpeed;
    public int bulletDamage;

    public Tower(TuioObject tobj) {
        this.tobj = tobj;
    }


}
