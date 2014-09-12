package com.sagamor.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.*;
import game.Config;
import game.descriptions.WaveDescription;
import game.descriptions.entities.CastleDescription;
import game.descriptions.entities.TowerDescription;
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
        Config.entityDescriptions = cfgJson.fromJson(IntMap.class, Gdx.files.internal("cfg/entities.json"));
        Config.monsterDescriptions = cfgJson.fromJson(ObjectMap.class, MonsterDescription.class, Gdx.files.internal("cfg/monsters.json"));
        Config.waveDescriptions = new Array<WaveDescription>();
        Config.app = this;
        for (JsonValue value : new JsonReader().parse(Gdx.files.internal("cfg/waves.json"))) {
            Config.waveDescriptions.add(cfgJson.readValue(WaveDescription.class, value));
        }
        setScreen(new CalibrateScreen(this));
    }
}
