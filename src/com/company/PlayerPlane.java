package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class PlayerPlane {
    public int x;
    public int y;
    public int speed;
    public Image image;
    public int planeWidth;
    public int planeHeight;

    public PlayerPlane(int Width, int Height, String Name, int speed) {
        planeWidth = Width;
        planeHeight = Height;
        x = GameWindow.frameWidthSize / 2 - planeWidth/2;
        y=GameWindow.frameHeightSize - planeWidth;
        image=GameWindow.loadImageFromFile(Name);
        this.speed=speed;
    }

    public void moveUp()
    {
        if(y>=30+speed)
            y-=speed;
    }
    public void moveDown()
    {
        if(y<=GameWindow.frameHeightSize-planeHeight-speed)
            y+=speed;
    }
    public void moveLeft()
    {
        if(x>=0+speed)
            x-=speed;
    }
    public void moveRight()
    {
        if(x<=GameWindow.frameWidthSize-planeWidth-speed)
            x+=speed;
    }
}
