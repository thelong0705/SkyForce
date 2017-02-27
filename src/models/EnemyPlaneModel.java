package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneModel {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;

    public EnemyPlaneModel(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
