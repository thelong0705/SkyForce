package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GameWindow extends Frame {
    Image backgroundImage;
    Image planeImage;
    Image enemyPlaneImage;
    Image bulletImage;
    int frameWidthSize = 400;
    int frameHeightSize = 600;
    int planeX = frameWidthSize / 2 - 35;
    int planeY = frameHeightSize - 50;
    int enemyPlaneX = frameWidthSize / 2 - 16;
    int enemyPlaneY = 30;
    int bulletX;
    int bulletY;
    boolean isBulletExist = false;
    private int SPEED = 10;
    private BufferedImage backBufferedImage;
    Thread thread;
    private Graphics backGraphics;
    //PlayerBullet playerBullet;
    ArrayList<PlayerBullet> playerBulletList=new ArrayList<PlayerBullet>();

    public GameWindow() {
        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                System.exit(0);
            }
        });
        //LoadImage
//        try {
//            backgroundImage= ImageIO.read(new File("resources/background.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        backgroundImage = loadImageFromFile("background.png");
        planeImage = loadImageFromFile("plane3.png");
        enemyPlaneImage = loadImageFromFile("enemy_plane_white_1.png");
        //bulletImage=loadImageFromFile("bullet.png");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        PlayerBullet playerBullet = new PlayerBullet();
                        playerBullet.image = loadImageFromFile("bullet.png");
                        playerBullet.x = planeX+35-6;
                        playerBullet.y = planeY-30;
                        playerBullet.speed=10;
                        playerBulletList.add(playerBullet);

                        break;
                    case KeyEvent.VK_RIGHT:
                        if (planeX + SPEED > frameWidthSize - 70) ;
                        else
                            planeX += SPEED;


                        break;
                    case KeyEvent.VK_LEFT:
                        if (planeX - SPEED < 0) ;
                        else
                            planeX -= SPEED;


                        break;
                    case KeyEvent.VK_UP:
                        if (planeY - SPEED < 30) ;
                        else
                            planeY -= SPEED;


                        break;
                    case KeyEvent.VK_DOWN:
                        if (planeY + SPEED > frameHeightSize - 50) ;
                        else
                            planeY += SPEED;


                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }

        });

        backBufferedImage = new BufferedImage(frameWidthSize, frameHeightSize, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferedImage.getGraphics();

    }

    public void start() {
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage, 0, 0, frameWidthSize, frameHeightSize, null);
            backGraphics.drawImage(planeImage, planeX, planeY, 70, 50, null);
            backGraphics.drawImage(enemyPlaneImage, enemyPlaneX, enemyPlaneY, 32, 32, null);
            for(PlayerBullet temp: playerBulletList)
            {

                    backGraphics.drawImage(temp.image, temp.x, temp.y, 13, 30, null);
                    if(temp.y-temp.speed>0)
                        temp.y-=temp.speed;


            }


            if (enemyPlaneY + SPEED / 2 < frameHeightSize)
                enemyPlaneY += SPEED / 2;
            else
                enemyPlaneY = 30;
//            if(isBulletExist==true)
//            {
//                backGraphics.drawImage(bulletImage,bulletX,bulletY,13,33,null);
//                if(bulletY-10<0)
//                    isBulletExist=false;
//                else
//                    bulletY=bulletY-10;
//
//            }
            graphics.drawImage(backBufferedImage, 0, 0, null);
        }

    }

    private Image loadImageFromFile(String url) {
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/" + url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}