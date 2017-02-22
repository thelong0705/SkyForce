package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class BackGround {
    public Image image;
    public int x;
    public int y;
    public int speed;
    public int width;
    public int height;
    public BackGround(String name, int x, int y, int speed,int width,int height) {
        image= GameWindow.loadImageFromFile(name);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width=width;
        this.height=height;
    }
    public void moveDown()
    {
        y+=speed;
    }
}
