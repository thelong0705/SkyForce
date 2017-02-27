package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneModel {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;

    public PlayerPlaneModel(int x, int y, int speed, int width, int height) {
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

    public void moveUp()
    {
        if(y>=0+speed)
            y-=speed;
    }
    public void moveDown()
    {
        if(y<= GameWindow.frameHeightSize-height-speed)
            y+=speed;
    }
    public void moveLeft()
    {
        if(x>=0+speed)
            x-=speed;
    }
    public void moveRight()
    {
        if(x<=GameWindow.frameWidthSize-width-speed)
            x+=speed;
    }
}
