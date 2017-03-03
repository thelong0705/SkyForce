package com.company;

import controllers.*;
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
    public boolean isKeyLeft = false;
    public boolean isKeyRight = false;
    public boolean isKeyUp = false;
    public boolean isKeyDown = false;
    public boolean isSpace = false;
    public static final int frameWidthSize = 400;
    public static final int frameHeightSize = 600;
    public static final int BACKGROUNDSPEED = 1;
    public static final int PLAYERPLANESPEED = 5;
    public static final int ENEMYPLANESPEED = 1;
    public static final int PLAYERBULLETSPEED = 1;
    public static final int ENEMYBULLETSPEED = 3;
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
    private BufferedImage backBufferedImage;
    Thread thread;
    Thread thread1;
    private Graphics backGraphics;
    private PlayerPlaneController playerPlaneController;
    Vector<PlayerBulletController> playerBulletControllers = new Vector<>();
    ControllerManager controllerManager;
    public GameWindow() {
        controllerManager = new ControllerManager();
        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);

        playerPlaneController = new PlayerPlaneController(frameWidthSize / 2 - PLANEWIDTH / 2,
                frameHeightSize - PLANEHEIGHT, controllerManager.gameControllerVector);

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
                        isSpace=true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isKeyRight = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        isKeyLeft = true;
                        break;
                    case KeyEvent.VK_UP:
                        isKeyUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isKeyDown = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        isSpace=false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isKeyRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isKeyLeft = false;
                        break;
                    case KeyEvent.VK_UP:
                        isKeyUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isKeyDown = false;
                        break;
                }
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

                    if (isKeyRight)
                        playerPlaneController.moveRight();
                    if (isKeyLeft)
                        playerPlaneController.moveLeft();
                    if (isKeyUp)
                        playerPlaneController.moveUp();
                    if (isKeyDown)
                        playerPlaneController.moveDown();
                    if(isSpace&&cycleCounter%5==0) {
                        playerPlaneController.shoot();

                    }
                    if (cycleCounter % CYCLEBETWEENENEMYAPPEEAR == 0) {
                        randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
                        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(randomX, 0,
                                Utils.loadImageFromFile("enemy_plane_white_3.png"), EnemyPlaneController.Type.moveDownEnemy);
                        controllerManager.add(enemyPlaneController);
                    }
                    controllerManager.run();
                    controllerManager.checkOverLap();

                    repaint();
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
        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage.getImage(), backgroundImage.getX(), backgroundImage.getY(),
                    backgroundImage.getWidth(), backgroundImage.getHeight(), null);
            backGraphics.drawImage(backgroundImage2.getImage(), backgroundImage2.getX(), backgroundImage2.getY(),
                    backgroundImage2.getWidth(), backgroundImage2.getHeight(), null);
            backGraphics.drawImage(island2.getImage(), island2.getX(), island2.getY(), null);
            backGraphics.drawImage(island1.getImage(), island1.getX(), island1.getY(), null);

            playerPlaneController.draw(backGraphics);


            controllerManager.draw(backGraphics);


//            for (EnemyPlaneController temp : enemyPlaneExplosionList) {
//                temp.getView().drawExplosion(backGraphics, temp.getModel());
//            }

//            for(EnemyBulletController enemyBulletController: controllerManager.enemyBulletControllerVector)
//            {
//                enemyBulletController.draw(backGraphics);
//            }
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
