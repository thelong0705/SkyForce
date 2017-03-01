package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel{


    public EnemyBulletModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    public void fly()
    {
        y+=speed;
    }
}
