package com.company;

import java.awt.*;

/**
 * Created by Inpriron on 2/22/2017.
 */
public class PlayerPlane {
    private int x;
    private int y;
    private int speed;
    private Image image;
    private int planeWidth;

    public int getPlaneWidth() {
        return planeWidth;
    }

    public int getPlaneHeight() {
        return planeHeight;
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

    private int planeHeight;

    public PlayerPlane(int x, int y,String Name, int speed) {
        image=Utils.loadImageFromFile(Name);
        planeWidth = image.getWidth(null);
        planeHeight = image.getHeight(null);
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    public void moveUp()
    {
        if(y>=30+speed)
            y-=speed;
    }
    public void moveDown()
    {
        if(y<=GameWindow.frameHeightSize-planeHeight-speed)
            y+=speed;
    }
    public void moveLeft()
    {
        if(x>=0+speed)
            x-=speed;
    }
    public void moveRight()
    {
        if(x<=GameWindow.frameWidthSize-planeWidth-speed)
            x+=speed;
    }
    public void getHitByBullet(EnemyBullet enemyBullet) {
        int bulletLeftBottomX = enemyBullet.getX();
        int bulletLeftBottomY = enemyBullet.getY() + enemyBullet.getImage().getHeight(null);
        int bulletRightBottomX = enemyBullet.getX() + enemyBullet.getImage().getWidth(null);
        int bulletRightBottomY = bulletLeftBottomY;
        int playerBottomRightX = x + image.getWidth(null);
        int playerBottomRightY = y + image.getHeight(null);

        if ((bulletRightBottomX > x && bulletRightBottomX < playerBottomRightX &&
                bulletRightBottomY > y && bulletRightBottomY < playerBottomRightY)) {
            image = Utils.loadImageFromFile("explosion1.png");
        } else if ((bulletLeftBottomX > x && bulletLeftBottomX < playerBottomRightX &&
                bulletLeftBottomY > y && bulletLeftBottomY < playerBottomRightY)) {
            image = Utils.loadImageFromFile("explosion1.png");
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(image,x,y,planeWidth,planeHeight,null);
    }
}
