package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel {

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

    public void moveCrossToRight() {
        x += speed;
        y = x + x / 2;
        if (x > GameWindow.frameWidthSize && y > GameWindow.frameHeightSize) {
            x = y = 0;
        }
    }
}
