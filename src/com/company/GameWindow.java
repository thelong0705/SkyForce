package com.company;

import controllers.EnemyBulletController;
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
    public static final int PLAYERBULLETSPEED = 5;
    public static final int ENEMYBULLETSPEED = 10;
    public static final int PLANEWIDTH = 70;
    public static final int PLANEHEIGHT = 50;
    public static final int PLAYERBULLETWIDTH = 13;
    public static final int PLAYERBULLETHEIGHT = 30;
    public static final int ENEMYPLANEWIDTH = 32;
    public static final int ENEMYPLANEHEIGHT = 32;
    public static final int CYCLEBETWEENENEMYAPPEEAR = 60;
    public static final int ENEMYBULLETWIDTH = 9;
    public static final int ENEMYBULLETHEIGHT = 9;
    private BackGround backgroundImage;
    private BackGround backgroundImage2;
    Island island1;
    Island island2;
    EnemyBullet enemyBullet;
    PowerUp powerUp;
    ArrayList<EnemyBulletController> enemyBulletControllerList = new ArrayList<EnemyBulletController>();
    private BufferedImage backBufferedImage;
    Thread thread;
    Thread thread1;
    private Graphics backGraphics;
    private PlayerPlaneController playerPlaneController;
    ArrayList<EnemyPlaneController> enemyPlaneControllerList = new ArrayList<EnemyPlaneController>();
    ArrayList<PlayerBulletController> playerBulletList = new ArrayList<PlayerBulletController>();
    ArrayList<EnemyPlaneController> enemyPlaneExplosionList = new ArrayList<EnemyPlaneController>();

    public GameWindow() {

        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);

        playerPlaneController = new PlayerPlaneController(frameWidthSize / 2 - PLANEWIDTH / 2, frameHeightSize - PLANEHEIGHT);

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
                    if (cycleCounter % CYCLEBETWEENENEMYAPPEEAR == 0) {
                        randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
                        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(randomX, 0,
                                Utils.loadImageFromFile("enemy_plane_white_3.png"));
                        enemyPlaneControllerList.add(enemyPlaneController);

                    }

                    checkIfEnemyHitByPlayerBullet(enemyPlaneControllerList, playerBulletList);

                    Iterator<PlayerBulletController> iter = playerBulletList.iterator();
                    while (iter.hasNext()) {
                        PlayerBulletController temp = iter.next();

                        if (temp.getModel().getY() < 0) {
                            iter.remove();

                        } else {
                            temp.run();
                        }
                    }
                    Iterator<EnemyPlaneController> iter1 = enemyPlaneExplosionList.iterator();
                    while (iter1.hasNext()) {
                        EnemyPlaneController temp = iter1.next();
                        if (temp.getView().getStateOfExplosion() > 6 - 1) //Vi co 6 anh trang thai no
                            iter1.remove();
                    }
                    Iterator<EnemyBulletController> iter2 = enemyBulletControllerList.iterator();
                    while (iter2.hasNext()) {
                        EnemyBulletController temp = iter2.next();
                        if (temp.getModel().getY() > frameHeightSize) {
                            iter2.remove();
                        } else
                            temp.run();
                    }

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


    private void checkIfEnemyHitByPlayerBullet(ArrayList<EnemyPlaneController> enemyPlaneControllerList,
                                               ArrayList<PlayerBulletController> playerBulletControllerList) {
        int bulletCenterX;
        int bulletCenterY;
        int enemyX;
        int enemyY;
        boolean isHit;
        Iterator<EnemyPlaneController> iter = enemyPlaneControllerList.iterator();
        while (iter.hasNext()) {
            isHit = false;
            EnemyPlaneController enemyPlane = iter.next();
            enemyX = enemyPlane.getModel().getX();
            enemyY = enemyPlane.getModel().getY();
            Iterator<PlayerBulletController> iter1 = playerBulletControllerList.iterator();
            while (iter1.hasNext()) {
                PlayerBulletController playerBullet = iter1.next();
                bulletCenterX = playerBullet.getModel().getX() + playerBullet.getModel().getWidth() / 2;
                bulletCenterY = playerBullet.getModel().getY() + playerBullet.getModel().getHeight();
                if (bulletCenterX > enemyX && bulletCenterX < enemyX + enemyPlane.getModel().getWidth()
                        && bulletCenterY > enemyY && bulletCenterY < enemyY + enemyPlane.getModel().getHeight()) {
                    enemyPlaneExplosionList.add(new EnemyPlaneController(enemyX, enemyY, null));
                    iter1.remove();
                    isHit = true;
                    break;
                }
            }
            if (!isHit) {
                if (enemyPlane.getModel().getY() < frameHeightSize) {
                    enemyPlane.moveDown();
                    if (enemyPlane.getModel().getY() % 200 == 0)
                        enemyBulletControllerList = enemyPlane.shootBullet(enemyBulletControllerList);
                } else {
                    iter.remove();
                }
            } else {
                iter.remove();
            }

        }
    }

    public void start() {
        thread.start();
        thread1.start();
    }

    @Override
    public void update(Graphics graphics) {
        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage.getImage(), backgroundImage.getX(), backgroundImage.getY(),
                    backgroundImage.getWidth(), backgroundImage.getHeight(), null);
            backGraphics.drawImage(backgroundImage2.getImage(), backgroundImage2.getX(), backgroundImage2.getY(),
                    backgroundImage2.getWidth(), backgroundImage2.getHeight(), null);
            backGraphics.drawImage(island2.getImage(), island2.getX(), island2.getY(), null);
            backGraphics.drawImage(island1.getImage(), island1.getX(), island1.getY(), null);
            Iterator<EnemyPlaneController> iter = enemyPlaneControllerList.iterator();
            while (iter.hasNext()) {
                EnemyPlaneController temp = iter.next();
                temp.draw(backGraphics);

            }
            playerPlaneController.draw(backGraphics);

            Iterator<PlayerBulletController> iter1 = playerBulletList.iterator();
            while (iter1.hasNext()) {
                PlayerBulletController temp = iter1.next();
                temp.draw(backGraphics);
                System.out.println(playerBulletList.size());
            }

            Iterator<EnemyPlaneController> iter2 = enemyPlaneExplosionList.iterator();
            while (iter2.hasNext()) {
                EnemyPlaneController temp = iter2.next();
                temp.getView().drawExplosion(backGraphics, temp.getModel());
            }

            Iterator<EnemyBulletController> iter3 = enemyBulletControllerList.iterator();
            while (iter3.hasNext()) {
                EnemyBulletController temp = iter3.next();
                temp.draw(backGraphics);
            }
            if (powerUp != null) {
                backGraphics.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), null);
                powerUp.moveDown();
                if (powerUp.getY() > frameHeightSize)
                    powerUp = null;
            }

            backgroundImage.moveDown();
            backgroundImage2.moveDown();
            island1.moveDown();
            island2.moveDown();
            graphics.drawImage(backBufferedImage, 0, 0, null);
        }
    }

}
