package game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import game.Board;
import game.Config;
import game.descriptions.entities.upgrades.TowerUpgrade;
import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class UpgradeView extends Group {
    private final Tower tower;
    private final int x;
    private final int y;
    private final Array<TowerUpgrade> upgrades;

    public UpgradeView(Tower tower, int x, int y, Array<TowerUpgrade> upgrades) {
        this.tower = tower;
        this.x = x;
        this.y = y;
        this.upgrades = upgrades;
        setPosition(x * Board.CELL_SIZE, y * Board.CELL_SIZE);
        Label descLabel = new Label(upgrades.toString(), Config.skin);
        descLabel.setWrap(true);
        descLabel.setSize(Board.CELL_SIZE, Board.CELL_SIZE);
        addActor(descLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (tower.coordinate.x == x && tower.coordinate.y == y) {
            applyUpgrades();
            remove();
        }
    }

    private void applyUpgrades() {
        for (TowerUpgrade upgrade : upgrades) {
            upgrade.apply(tower);
        }
    }
}
