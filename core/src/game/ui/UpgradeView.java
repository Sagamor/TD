package game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import game.Board;
import game.Config;
import game.descriptions.UpgradeDescription;
import game.descriptions.entities.upgrades.TowerUpgrade;
import game.entities.Tower;

/**
 * Created by Sagamor on 13/09/2014.
 */
public class UpgradeView extends Group {
    private final Tower tower;
    private final int x;
    private final int y;
    private final UpgradeDescription upgradeDescription;
    private final Array<UpgradeView> upgradeViews;

    public UpgradeView(Tower tower, int x, int y, UpgradeDescription upgradeDescription, Array<UpgradeView> upgradeViews) {
        this.tower = tower;
        this.x = x;
        this.y = y;
        this.upgradeDescription = upgradeDescription;
        this.upgradeViews = upgradeViews;
        setPosition(x * Board.CELL_SIZE, y * Board.CELL_SIZE);
        Label descLabel = new Label(upgradeDescription.toString(), Config.skin);
        descLabel.setWrap(true);
        descLabel.setSize(Board.CELL_SIZE*0.75f, Board.CELL_SIZE*0.75f);
        addActor(descLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (tower.coordinate.x == x && tower.coordinate.y == y) {
            applyUpgrades();
            for (UpgradeView upgradeView : upgradeViews) {
                upgradeView.remove();
            }
        }

    }

    private void applyUpgrades() {
        for (TowerUpgrade upgrade : upgradeDescription.upgrades) {
            upgrade.apply(tower);
            System.out.println("apply " + upgrade);
        }
    }
}
