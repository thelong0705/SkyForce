package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {

    public PlayerBulletModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    public void fly()
    {
        y-=speed;
    }
    public void moveCrossLeft()
    {
        x-=speed;
        y-=speed;
    }
    public void moveCrossRight()
    {
        x+=speed;
        y-=speed;
    }
}
