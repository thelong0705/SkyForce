package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/21/2017.
 */
public class PlayerBullet {
    private int x;
    private int y;
    private int speed;
    private Image image;
    private int bulletWidth;
    private int bulletHeight;
    private int isHit;
    public PlayerBullet(int playerPlaneX, int playerPlaneY, int playerPlaneWidth,
                        int speed, String Name, int bulletWidth, int bulletHeight) {
        x = playerPlaneX + playerPlaneWidth / 2 - bulletWidth / 2;
        y = playerPlaneY - bulletHeight;
        this.speed = speed;
        image = Utils.loadImageFromFile(Name);
        this.bulletWidth = bulletWidth;
        this.bulletHeight = bulletHeight;
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

    public Image getImage() {
        return image;
    }

    public void setIsHit(int isHit) {
        this.isHit = isHit;
    }

    public int getBulletWidth() {

        return bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public int getIsHit() {
        return isHit;
    }

    public void moveUp() {
        y -= speed;
    }


}
