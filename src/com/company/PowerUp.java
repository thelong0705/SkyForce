package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/24/2017.
 */
public class PowerUp {
    Image image;
    int x;
    int y;
    int speed;

    public PowerUp(String name, int x, int y, int speed) {
        image = GameWindow.loadImageFromFile(name);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveDown() {
        y += speed;
    }
}
