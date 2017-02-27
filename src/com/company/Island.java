package com.company;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Inpriron on 2/23/2017.
 */
public class Island {
    private Image image;
    private int x;
    private int y;
    private int speed;

    public Island(String Name, int x, int y, int speed) {
        this.image = Utils.loadImageFromFile(Name);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Image getImage() {
        return image;
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

    public void moveDown()
    {
        y+=speed;
        if(y> GameWindow.frameHeightSize)
        {
            x= ThreadLocalRandom.current().nextInt(0, GameWindow.frameWidthSize);
            y=0;
        }
    }

}
