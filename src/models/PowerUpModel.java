package models;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class PowerUpModel extends GameModel {
    public PowerUpModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }
    public void moveDown()
    {
        y+=speed;
    }
}
