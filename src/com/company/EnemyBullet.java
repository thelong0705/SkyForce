package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/23/2017.
 */
public class EnemyBullet {
    Image image;
    int x;
    int y;
    int speed;

    public EnemyBullet(String Name, int speed,Image enemyImage,int enemyX, int enemyY) {
        this.image = GameWindow.loadImageFromFile(Name);
        x=enemyX + enemyImage.getWidth(null)/2 - image.getWidth(null)/2;
        y=enemyY + enemyImage.getHeight(null);
        this.speed = speed;
    }
    public void moveDown()
    {
        y+=speed;
    }
}
