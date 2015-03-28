package game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
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

//        Image image = new Image(Config.skin, upgradeDescription.icon);
//        image.setSize(Board.CELL_SIZE, Board.CELL_SIZE);
//        image.setScaling(Scaling.none);
//        image.setAlign(Align.center);

        Label descLabel = new Label(upgradeDescription.icon, Config.skin);
        descLabel.setWrap(true);
        descLabel.setSize(Board.CELL_SIZE, Board.CELL_SIZE);
        descLabel.setAlignment(Align.center);
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
