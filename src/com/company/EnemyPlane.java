package com.company;

import controllers.PlayerBulletController;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class EnemyPlane {
    private int x;
    private int y;private int speed;
    private Image image;
    private int planeWidth;
    private int planeHeight;
    private int stateOfExplosion;

    public void setStateOfExplosion(int stateOfExplosion) {
        this.stateOfExplosion = stateOfExplosion;
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

    public int getPlaneWidth() {
        return planeWidth;
    }

    public int getPlaneHeight() {
        return planeHeight;
    }

    public int getStateOfExplosion() {
        return stateOfExplosion;
    }

    public EnemyPlane(int x, int y, String Name, int speed) {
        this.x = x;
        this.y = y;
        image = Utils.loadImageFromFile(Name);
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

//    public boolean getHitByPlayerBullet(PlayerBulletController playerBulletController)
//    {
//        int bulletCenterX=playerBullet.getX()+playerBullet.getImage().getWidth(null)/2;
//        int bulletCenterY=playerBullet.getY()+playerBullet.getImage().getHeight(null);
//        if(bulletCenterX>x&&bulletCenterX<x+image.getWidth(null)&&
//                bulletCenterY>y&&bulletCenterY<y+image.getHeight(null))
//        {
//            stateOfExplosion=1;
//            playerBullet.setIsHit(1);
//            return true;
//        }
//        return false;
//    }

   public void enemyBlowUp()
    {
        if(stateOfExplosion<=6) {
            image = Utils.loadImageFromFile("explosion" + stateOfExplosion + ".png");

        }
        stateOfExplosion++;

    }
}
