package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/24/2017.
 */
public class PowerUp {
    private Image image;
    private int x;
    private int y;
    private int speed;

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

    public PowerUp(String name, int x, int y, int speed) {
        image = Utils.loadImageFromFile(name);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveDown() {
        y += speed;
    }
}
