package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletModel {
    private static final int SPEED = GameWindow.ENEMYBULLETSPEED;
    private int x;
    private int y;
    private int width;
    private int height;

    public EnemyBulletModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void fly()
    {
        y+=SPEED;
    }
}
