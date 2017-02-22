package com.company;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Inpriron on 2/23/2017.
 */
public class Island {
    Image image;
    int x;
    int y;
    int speed;

    public Island(String Name, int x, int y, int speed) {
        this.image = GameWindow.loadImageFromFile(Name);
        this.x = x;
        this.y = y;
        this.speed = speed;
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
