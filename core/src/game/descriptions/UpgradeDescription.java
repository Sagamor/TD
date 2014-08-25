package game.descriptions;

import com.badlogic.gdx.utils.*;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class UpgradeDescription implements Json.Serializable {

    public ObjectIntMap<UpgradeDescription> towerUpgrades;

    public UpgradeDescription() {
    }

    @Override public void write(Json json) {}

    @Override public void read(Json json, JsonValue jsonData) {
        towerUpgrades = new ObjectIntMap<UpgradeDescription>();
        jsonData = jsonData.get("cards");
        for (int i = 0; i < jsonData.size; i++) {
            JsonValue info = jsonData.get(i);
//            GameEvent event = GameEvents.valueOf(info.getString("value"));
            int stability = info.getInt("stability");
            int count = info.getInt("count");
//            GameEventType eventType = GameEventType.valueOf(info.getString("type"));
//            towerUpgrades.put(new UpgradeDescription(eventType, event, stability), count);
        }
    }

    @Override public String toString() {
        return "TowerUpgrades{" +
                "upgrades=" + towerUpgrades +
                "}";
    }
}
