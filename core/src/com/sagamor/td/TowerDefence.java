package com.sagamor.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.*;
import game.Config;
import game.GameSettings;
import game.descriptions.Point;
import game.descriptions.UpgradeDescription;
import game.descriptions.WaveDescription;
import game.descriptions.entities.CastleDescription;
import game.descriptions.entities.TowerDescription;
import game.descriptions.entities.upgrades.AddUpgrade;
import game.descriptions.entities.upgrades.MultiplyUpgrade;
import game.descriptions.entities.upgrades.TowerUpgrade;
import game.descriptions.entities.upgrades.TowerUpgrades;
import game.descriptions.monsters.MonsterDescription;
import screens.CalibrateScreen;

@SuppressWarnings("unchecked")
public class TowerDefence extends Game {

    @Override
    public void create() {
        Config.skin = new Skin(Gdx.files.internal("skin/skin.json"));
        Json cfgJson = new Json();
        cfgJson.setTypeName("type");
        cfgJson.addClassTag("tower", TowerDescription.class);
        cfgJson.addClassTag("castle", CastleDescription.class);
        cfgJson.addClassTag("add", AddUpgrade.class);
        cfgJson.addClassTag("multiply", MultiplyUpgrade.class);
        cfgJson.setSerializer(IntMap.class, new Json.ReadOnlySerializer<IntMap>() {
            @Override
            public IntMap read(Json json, JsonValue jsonData, Class type) {
                IntMap res = new IntMap();
                for (JsonValue value : jsonData) {
                    System.out.println("name=" + value.name());
                    res.put(Integer.parseInt(value.name()), json.readValue(null, value));
                }
                return res;
            }
        });
        cfgJson.setSerializer(IntArray.class, new Json.ReadOnlySerializer<IntArray>() {
            @Override
            public IntArray read(Json json, JsonValue jsonData, Class type) {
                IntArray res = new IntArray();
                for (JsonValue v : jsonData) {
                    res.add(v.asInt());
                }
                return res;
            }
        });
        cfgJson.setSerializer(TowerUpgrades.class, new Json.ReadOnlySerializer<TowerUpgrades>() {
            @Override
            public TowerUpgrades read(Json json, JsonValue jsonData, Class type) {
                TowerUpgrades ups = new TowerUpgrades();
                for (JsonValue out : jsonData) {
                    IntMap<Array<UpgradeDescription>> fuuuu = new IntMap();
                    for (JsonValue value : out) {
                        Array<UpgradeDescription> list = new Array<UpgradeDescription>();
                        for (JsonValue v : value) {
                            list.add(json.readValue(UpgradeDescription.class, v));
                        }
                        fuuuu.put(Integer.parseInt(value.name), list);
                    }
                    ups.put(Integer.parseInt(out.name), fuuuu);
                }

                return ups;
            }
        });
        cfgJson.setSerializer(GameSettings.class, new Json.ReadOnlySerializer<GameSettings>() {
            @Override public GameSettings read(Json json, JsonValue jsonData, Class type) {
                Array<WaveDescription> waves = json.readValue(Array.class, WaveDescription.class, jsonData.get("waves"));
                ObjectMap<String, Point> rawPositions = json.readValue(ObjectMap.class, Point.class, jsonData.get("positions"));
                IntMap<Point> positions = new IntMap<Point>();
                for (ObjectMap.Entry<String, Point> e : rawPositions) {
                    positions.put(Integer.parseInt(e.key), e.value);
                }
                return new GameSettings(positions, waves);
            }
        });
        Config.entityDescriptions = cfgJson.fromJson(IntMap.class, Gdx.files.internal("cfg/entities.json"));
        Config.monsterDescriptions = cfgJson.fromJson(ObjectMap.class, MonsterDescription.class, Gdx.files.internal("cfg/monsters.json"));
        Config.upgrades = cfgJson.fromJson(TowerUpgrades.class, Gdx.files.internal("cfg/upgrades.json"));
        Config.gameSettings = cfgJson.fromJson(OrderedMap.class, GameSettings.class, Gdx.files.internal("cfg/games.json"));
        Config.app = this;
        System.out.println("settings = "+Config.gameSettings);
        setScreen(new CalibrateScreen(this));
    }
}
