package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class BackGround {
    private Image image;
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    public BackGround(String name, int x, int y, int speed,int width,int height) {
        image= Utils.loadImageFromFile(name);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width=width;
        this.height=height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveDown()
    {
        y+=speed;
        if(y>GameWindow.frameHeightSize)

            y=-GameWindow.frameHeightSize;
    }
}
