package models;

import com.company.GameWindow;

import java.awt.*;

/**
 * Created by Inpriron on 3/7/2017.
 */
public class PlayerBombModel extends GameModel {
    private int explosionAOEWidth = GameWindow.frameWidthSize;

    private int explosionAOEHeight = GameWindow.frameHeightSize ;

    public PlayerBombModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    public void moveUp() {
        y-=speed;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(0, 0, explosionAOEWidth, explosionAOEHeight);
    }

    @Override
    public boolean intersects(GameModel other) {
        return super.intersects(other);
    }
}