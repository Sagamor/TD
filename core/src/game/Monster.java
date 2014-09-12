package game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.descriptions.monsters.MonsterDescription;
import game.entities.Castle;

/**
 * Created by Sagamor on 25/08/2014.
 */
public class Monster extends Group {

    private Vector2 speed;
    private Board board;

    public Monster(MonsterDescription desc, Board board) {
        this.board = board;
        speed = new Vector2(0, desc.speed * Board.CELL_SIZE);
        Image image = new Image(Config.skin, desc.image);
        image.setCenterPosition(0, 0);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        addActor(image);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Castle nearestCastle = getNearestCastle(board);
        if (nearestCastle == null)
            return;

        float angle = (float) (Math.atan2(nearestCastle.getY() - getY(),nearestCastle.getX() - getX()) * 180f / Math.PI);
//        System.out.println(angle);
        speed.setAngle(angle);
        moveBy(speed.x*delta, speed.y*delta);

    }

    private Castle getNearestCastle(Board board) {
        Castle res = null;
        float minDistance = Float.MAX_VALUE;
        for (Castle castle : board.castles.values()) {
            float dx = getX() - castle.getX();
            float dy = getY() - castle.getY();
            float distance = dx * dx + dy * dy;
            if (distance < minDistance) {
                res = castle;
                minDistance = distance;
            }
        }
        return res;
    }

}
