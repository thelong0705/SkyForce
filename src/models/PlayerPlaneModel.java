package models;

import com.company.GameWindow;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel {


    public PlayerPlaneModel(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
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
    public double getMidX()
    {
        return x+width/2.0;
    }


}
