package com.company;

import controllers.EnemyPlaneController;
import controllers.PlayerBulletController;
import controllers.PlayerPlaneController;
import models.PlayerBulletModel;
import views.PlayerBulletView;

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
import java.util.concurrent.ThreadLocalRandom;

public class GameWindow extends Frame {
    //Image backgroundImage;
    public static final int frameWidthSize = 400;
    public static final int frameHeightSize = 600;
    public static final int BACKGROUNDSPEED = 1;
    public static final int PLAYERPLANESPEED = 10;
    public static final int ENEMYPLANESPEED = 2;
    public static final int PLAYERBULLETSPEED = 10;
    public static final int ENEMYBULLETSPEED = 10;
    public static final int PLANEWIDTH = 70;
    public static final int PLANEHEIGHT = 50;
    public static final int PLAYERBULLETWIDTH = 13;
    public static final int PLAYERBULLETHEIGHT = 30;
    public static final int ENEMYPLANEWIDTH = 32;
    public static final int ENEMYPLANEHEIGHT = 32;
    public static final int CYCLEBETWEENENEMYAPPEEAR=60;
    private BackGround backgroundImage;
    private BackGround backgroundImage2;
    //    PlayerPlane playerPlane;
//    EnemyPlane enemyPlaneDown;
//    EnemyPlane enemyPlaneCross1;
//    EnemyPlane enemyForExplosion;
    Island island1;
    Island island2;
    EnemyBullet enemyBullet;
    PowerUp powerUp;
    ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();
    private BufferedImage backBufferedImage;
    Thread thread;
    Thread thread1;
    private Graphics backGraphics;
    private PlayerPlaneController playerPlaneController;
    //PlayerBullet playerBullet;
    //ArrayList<PlayerBullet> playerBulletList = new ArrayList<PlayerBullet>();
    ArrayList<EnemyPlaneController> enemyPlaneControllerList = new ArrayList<EnemyPlaneController>();
    ArrayList<PlayerBulletController> playerBulletList = new ArrayList<PlayerBulletController>();

    public GameWindow() {

        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);
        //PlayerBulletModel model= new PlayerBulletModel(300,300,13,30);
        //PlayerBulletView view= new PlayerBulletView(Utils.loadImageFromFile("bullet.png"));
        //playerBulletController= new PlayerBulletController(model,view);
//        playerPlane = new PlayerPlane(frameWidthSize / 2 - PLANEWIDTH / 2, frameHeightSize - PLANEHEIGHT,
//                "plane3.png", PLAYERPLANESPEED);
        playerPlaneController = new PlayerPlaneController(frameWidthSize / 2 - PLANEWIDTH / 2, frameHeightSize - PLANEHEIGHT);
//        enemyPlaneDown = new EnemyPlane(frameWidthSize / 2 - 32 / 2, 0,
//                "enemy_plane_white_3.png", ENEMYPLANESPEED);
//
//        enemyPlaneCross1 = new EnemyPlane(0, 0, "enemy-green-1.png", ENEMYPLANESPEED);
        island1 = new Island("island.png", 200, 200, BACKGROUNDSPEED);
        island2 = new Island("island-2.png", 50, 400, BACKGROUNDSPEED);
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
        backgroundImage = new BackGround("background1.png", 0, 0,
                BACKGROUNDSPEED, frameWidthSize, frameHeightSize);
        backgroundImage2 = new BackGround("background1.png", 0, -frameHeightSize,
                BACKGROUNDSPEED, frameWidthSize, frameHeightSize);
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
                        playerBulletList = playerPlaneController.shootBullet(playerBulletList);
                        break;
                    case KeyEvent.VK_RIGHT:
                        playerPlaneController.moveRight();
                        break;
                    case KeyEvent.VK_LEFT:
                        playerPlaneController.moveLeft();
                        break;
                    case KeyEvent.VK_UP:
                        playerPlaneController.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        playerPlaneController.moveDown();
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
                int cycleCounter = 0;
                int randomX;
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                    Iterator<PlayerBulletController> iter = playerBulletList.iterator();
                    while (iter.hasNext()) {
                        PlayerBulletController temp = iter.next();
                        if (temp.getModel().getY() > frameHeightSize)
                            iter.remove();
                        else {
                            temp.run();
                        }
                    }
                    if(cycleCounter%CYCLEBETWEENENEMYAPPEEAR==0)
                    {
                        randomX=ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
                        EnemyPlaneController enemyPlaneController= new EnemyPlaneController(randomX,0,
                                Utils.loadImageFromFile( "enemy_plane_white_3.png"));
                        enemyPlaneControllerList.add(enemyPlaneController);
                        System.out.println("where is my plane");
                    }
                    Iterator<EnemyPlaneController> iter1 = enemyPlaneControllerList.iterator();
                    while (iter1.hasNext()) {
                        EnemyPlaneController temp = iter1.next();
                        temp.moveDown();
                    }
                    System.out.println(cycleCounter);
                    cycleCounter++;
                }
            }

        });
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize - 50);
                    int randomY = ThreadLocalRandom.current().nextInt(50, 200);
                    powerUp = new PowerUp("power-up.png", randomX, randomY, 3);
                }
            }
        });
        backBufferedImage = new BufferedImage(frameWidthSize, frameHeightSize, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferedImage.getGraphics();
    }

    public void start() {
        thread.start();
        thread1.start();
    }

    @Override
    public void update(Graphics graphics) {

//        if (enemyPlaneDown.getY() > frameHeightSize) {
//            int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
//            enemyPlaneDown = new EnemyPlane(randomX, 0, "enemy_plane_white_3.png", ENEMYPLANESPEED);
//        }
//        if (enemyPlaneDown.getY() == 0) {
//            enemyBullet = new EnemyBullet("enemy_bullet.png", ENEMYBULLETSPEED,
//                    enemyPlaneDown.getImage(), enemyPlaneDown.getX(), enemyPlaneDown.getY());
//            enemyBulletList.add(enemyBullet);
//        }
//        if (enemyPlaneCross1.getX() % 100 == 0) {
//            enemyBullet = new EnemyBullet("enemy_bullet.png", ENEMYBULLETSPEED / 2,
//                    enemyPlaneCross1.getImage(), enemyPlaneCross1.getX(), enemyPlaneCross1.getY());
//            enemyBulletList.add(enemyBullet);
//        }

        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage.getImage(), backgroundImage.getX(), backgroundImage.getY(),
                    backgroundImage.getWidth(), backgroundImage.getHeight(), null);
            backGraphics.drawImage(backgroundImage2.getImage(), backgroundImage2.getX(), backgroundImage2.getY(),
                    backgroundImage2.getWidth(), backgroundImage2.getHeight(), null);
            Iterator<EnemyPlaneController> iter = enemyPlaneControllerList.iterator();
            while (iter.hasNext()) {
                System.out.println("here it is");;
                EnemyPlaneController temp = iter.next();
                temp.draw(backGraphics);
            }
            backGraphics.drawImage(island2.getImage(), island2.getX(), island2.getY(), null);
            backGraphics.drawImage(island1.getImage(), island1.getX(), island1.getY(), null);
//            backGraphics.drawImage(playerPlane.getImage(), playerPlane.getX(), playerPlane.getY(),
//                    playerPlane.getImage().getWidth(null), playerPlane.getImage().getHeight(null), null);
            playerPlaneController.draw(backGraphics);
//            backGraphics.drawImage(enemyPlaneDown.getImage(), enemyPlaneDown.getX(), enemyPlaneDown.getY(),
//                    enemyPlaneDown.getPlaneWidth(), enemyPlaneDown.getPlaneHeight(), null);
//            backGraphics.drawImage(enemyPlaneCross1.getImage(), enemyPlaneCross1.getX(), enemyPlaneCross1.getY(),
//                    enemyPlaneCross1.getPlaneWidth(), enemyPlaneCross1.getPlaneHeight(), null);
            for (EnemyBullet temp : enemyBulletList) {
                backGraphics.drawImage(temp.getImage(), temp.getX(), temp.getY(), null);
                temp.moveDown();

            }

//            Iterator<PlayerBulletController> iter = playerBulletList.iterator();
//            while (iter.hasNext()) {
//                PlayerBulletController temp = iter.next();
//                if (enemyPlaneDown.getHitByPlayerBullet(temp)) {
//                    iter.remove();
//                    int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
//                    enemyForExplosion = enemyPlaneDown;
//                    enemyPlaneDown = new EnemyPlane(randomX, 0,
//                            "enemy_plane_white_3.png", ENEMYPLANESPEED);
//                } else if (enemyPlaneCross1.getHitByPlayerBullet(temp)) {
//                    iter.remove();
//                    enemyForExplosion = enemyPlaneCross1;
//                    enemyPlaneCross1 = new EnemyPlane(0, 0, "enemy-green-1.png", ENEMYPLANESPEED);
//                } else {
//                    backGraphics.drawImage(temp.getImage(), temp.getX(), temp.getY(), temp.getBulletWidth(), temp.getBulletHeight(), null);
//                    temp.moveUp();
//                }
//
//            }
            Iterator<PlayerBulletController> iter1 = playerBulletList.iterator();
            while (iter1.hasNext()) {
                PlayerBulletController temp = iter1.next();
                temp.draw(backGraphics);
            }
//            if (enemyForExplosion != null) {
//                enemyForExplosion.enemyBlowUp();
//                backGraphics.drawImage(enemyForExplosion.getImage(), enemyForExplosion.getX(), enemyForExplosion.getY(), null);
//                if (enemyForExplosion.getStateOfExplosion() > 6)
//                    enemyForExplosion = null;
//            }
            if (powerUp != null) {
                backGraphics.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), null);
                powerUp.moveDown();
                if (powerUp.getY() > frameHeightSize)
                    powerUp = null;
            }

//            enemyPlaneDown.moveDown();
//            enemyPlaneCross1.moveCrossToRight();
            backgroundImage.moveDown();
            backgroundImage2.moveDown();
            island1.moveDown();
            island2.moveDown();
            graphics.drawImage(backBufferedImage, 0, 0, null);
        }

    }


}
