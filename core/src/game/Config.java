package game;

import TUIO.TuioClient;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import game.descriptions.WaveDescription;
import game.descriptions.entities.EntityDescription;
import game.descriptions.monsters.MonsterDescription;

/**
 * Created by Sagamor on 17/03/2014.
 */
public class Config {

    public static Skin skin;
    public static IntMap<EntityDescription> entityDescriptions;
    public static ObjectMap<String, MonsterDescription> monsterDescriptions;
    public static Array<WaveDescription> waveDescriptions;
    public static Game app;
    public static TuioClient tuio;
//    public static ObjectMap<String, ParticleEffectPool> effects = new ObjectMap<String, ParticleEffectPool>();

}
