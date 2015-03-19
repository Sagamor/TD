package game;

import TUIO.TuioClient;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import game.descriptions.entities.EntityDescription;
import game.descriptions.entities.upgrades.TowerUpgrades;
import game.descriptions.monsters.MonsterDescription;

/**
 * Created by Sagamor on 17/03/2014.
 */
public class Config {

    public static Skin skin;
    public static IntMap<EntityDescription> entityDescriptions;
    public static ObjectMap<String, MonsterDescription> monsterDescriptions;
    public static ObjectMap<String, GameSettings> gameSettings;
    public static Game app;
    public static TuioClient tuio;
    public static TowerUpgrades upgrades;

}
