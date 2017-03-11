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
    public static final int BACK_GROUND_SPEED = 1;
    public static final int PLAYER_PLANE_SPEED = 5;
    public static final int ENEMY_PLANE_SPEED = 2;
    public static final int PLAYER_BULLET_SPEED = 3;
    public static final int ENEMY_BULLET_SPEED = 3;
    public static final int PLANE_WIDTH = 70;
    public static final int PLANE_HEIGHT = 50;
    public static final int PLAYER_BULLET_WIDTH = 13;
    public static final int PLAYER_BULLET_HEIGHT = 30;
    public static final int ENEMY_PLANE_WIDTH = 32;
    public static final int ENEMY_PLANE_HEIGHT = 32;
    public static final int CYCLE_BETWEEN_ENEMYAPPEEAR = 60;
    public static final int CYCLE_BETWEEN_ENEMY_CROSS_APPEAR = 60*3;
    public static final int CYCLE_BETWEEN_POWER_UP_APPEAR=60*4;
    public static final int ENEMY_BULLET_WIDTH = 9;
    public static final int ENEMY_BULLET_HEIGHT = 9;
    public static final int POWER_UP_WIDTH = 24;
    public static final int POWER_UP_HEIGHT = 24;
    public static final int POWER_UP_SPEED = 3;
    private BackGround backgroundImage;
    private BackGround backgroundImage2;
    Island island1;
    Island island2;
    private BufferedImage backBufferedImage;
    Thread thread;
    Thread thread1;
    private Graphics backGraphics;
    public static ControllerManager controllerManager;
    public GameWindow() {
        controllerManager = new ControllerManager();
        controllerManager.add(PlayerPlaneController.instance);
        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);
        island1 = new Island("island.png", 200, 200, BACK_GROUND_SPEED);
        island2 = new Island("island-2.png", 50, 400, BACK_GROUND_SPEED);
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
                BACK_GROUND_SPEED, frameWidthSize, frameHeightSize);
        backgroundImage2 = new BackGround("background1.png", 0, -frameHeightSize,
                BACK_GROUND_SPEED, frameWidthSize, frameHeightSize);

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
                        PlayerPlaneController.instance.moveRight();
                    if (isKeyLeft)
                        PlayerPlaneController.instance.moveLeft();
                    if (isKeyUp)
                        PlayerPlaneController.instance.moveUp();
                    if (isKeyDown)
                        PlayerPlaneController.instance.moveDown();
                    if(isSpace&&cycleCounter%5==0) {
                        PlayerPlaneController.instance.shoot();

                    }
//                    if (cycleCounter % CYCLE_BETWEEN_ENEMYAPPEEAR == 0) {
//                        randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
//                        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(randomX, 0,
//                                Utils.loadImageFromFile("enemy_plane_white_3.png"), EnemyPlaneController.Type.moveDownEnemy);
//                        controllerManager.add(enemyPlaneController);
//                    }
//                    if(cycleCounter % CYCLE_BETWEEN_ENEMY_CROSS_APPEAR==0)
//                    {
//                        EnemyPlaneController enemyPlaneController = new EnemyPlaneController(0, 0,
//                                Utils.loadImageFromFile("enemy-green-1.png"), EnemyPlaneController.Type.moveCrossEnemy);
//                        controllerManager.add(enemyPlaneController);
//                    }
//                    if(cycleCounter%CYCLE_BETWEEN_POWER_UP_APPEAR==0&&cycleCounter!=0)
//                    {
//                        randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
//                        PowerUpController powerUpController= new PowerUpController(randomX,0);
//                        controllerManager.add(powerUpController);
//                    }
                    controllerManager.run();
                    controllerManager.checkOverLap();
                    repaint();
                    cycleCounter++;
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
            backGraphics.drawImage(backgroundImage.getImage(), backgroundImage.getX(), backgroundImage.getY(),
                    backgroundImage.getWidth(), backgroundImage.getHeight(), null);
            backGraphics.drawImage(backgroundImage2.getImage(), backgroundImage2.getX(), backgroundImage2.getY(),
                    backgroundImage2.getWidth(), backgroundImage2.getHeight(), null);
            backGraphics.drawImage(island2.getImage(), island2.getX(), island2.getY(), null);
            backGraphics.drawImage(island1.getImage(), island1.getX(), island1.getY(), null);
            controllerManager.draw(backGraphics);
            backgroundImage.moveDown();
            backgroundImage2.moveDown();
            island1.moveDown();
            island2.moveDown();
            graphics.drawImage(backBufferedImage, 0, 0, null);
        }
    }
}
