package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class EnemyPlane {
    public int x;
    public int y;
    public int speed;
    public Image image;
    public int planeWidth;
    public int planeHeight;

    public EnemyPlane(int x, int y, int Width, int Height, String Name, int speed) {
        this.x = x;
        this.y = y;
        planeWidth = Width;
        planeHeight = Height;
//        x = GameWindow.frameWidthSize / 2 - planeWidth / 2;
//        y = GameWindow.frameHeightSize - planeWidth;
        image = GameWindow.loadImageFromFile(Name);
        this.speed = speed;
    }

    public void moveUp() {

        y -= speed;
    }

    public void moveDown() {

        y += speed;
    }

    public void moveLeft() {

        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveCrossToRight(){
        x+=speed;
        y=x+x/2;
    }
}
