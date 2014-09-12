package game.entities;

import TUIO.TuioObject;
import game.Board;
import game.Config;
import game.HasHp;
import game.Marker;
import game.descriptions.entities.CastleDescription;
import game.ui.HealthBar;
import screens.LoseScreen;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Castle extends Marker implements HasHp {

    private final CastleDescription castleDescription;
    private final HealthBar bar;
    private float hp;

    public Castle(TuioObject tobj, CastleDescription castleDescription) {
        super(tobj);
        this.castleDescription = castleDescription;
        hp = castleDescription.hp;
        bar = new HealthBar(this);
        addActor(bar);
        bar.setY(Board.CELL_SIZE - 5);
        bar.setSize(Board.CELL_SIZE, 5);
    }

    public float getHp() {
        return hp;
    }

    @Override
    public float getTotalHp() {
        return castleDescription.hp;
    }

    public void setHp(float hp) {
        if (this.hp == hp)
            return;
        this.hp = hp;
        System.out.println("castle hp: "+hp);
        if (hp <= 0) {
            Config.app.setScreen(new LoseScreen());
        }
    }
}
