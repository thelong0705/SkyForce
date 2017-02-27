package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/23/2017.
 */
public class EnemyBullet {
    private Image image;
    private int x;

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

    private int y;
    private int speed;

    public EnemyBullet(String Name, int speed,Image enemyImage,int enemyX, int enemyY) {
        this.image = Utils.loadImageFromFile(Name);
        x=enemyX + enemyImage.getWidth(null)/2 - image.getWidth(null)/2;
        y=enemyY + enemyImage.getHeight(null);
        this.speed = speed;
    }
    public void moveDown()
    {
        y+=speed;
    }
}
