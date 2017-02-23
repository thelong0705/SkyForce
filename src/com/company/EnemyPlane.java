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
    public int stateOfExplosion;
    public EnemyPlane(int x, int y, String Name, int speed) {
        this.x = x;
        this.y = y;
        image = GameWindow.loadImageFromFile(Name);
        planeWidth = image.getWidth(null);
        planeHeight = image.getHeight(null);
        this.speed = speed;
        stateOfExplosion=0;
    }

    public void moveUp() {

        y -= speed;
    }

    public void moveDown() {

        y += speed;
//        if(y>GameWindow.frameHeightSize)
//            y=0;
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
        if(x>GameWindow.frameWidthSize&&y>GameWindow.frameHeightSize)
        {
            x=y=0;
        }
    }

    public boolean getHitByPlayerBullet(PlayerBullet playerBullet)
    {
        int bulletCenterX=playerBullet.x+playerBullet.image.getWidth(null)/2;
        int bulletCenterY=playerBullet.y+playerBullet.image.getHeight(null);
        if(bulletCenterX>x&&bulletCenterX<x+image.getWidth(null)&&
                bulletCenterY>y&&bulletCenterY<y+image.getHeight(null))
        {
            stateOfExplosion=1;
            playerBullet.isHit=1;
            return true;
        }
        return false;
    }

}
