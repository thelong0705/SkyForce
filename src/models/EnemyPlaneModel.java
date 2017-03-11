package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel {

    public enum EnemyType {
        StraightDown,
        CrossRight
    }
    private EnemyType enemyType;
    public EnemyPlaneModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    public void moveUp() {

        y -= speed;
    }

    public void moveDown() {

        y += speed;

    }

    public void moveLeft() {

        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }


}
