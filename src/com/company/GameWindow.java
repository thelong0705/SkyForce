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
import java.util.concurrent.ThreadLocalRandom;

public class GameWindow extends Frame {
    //Image backgroundImage;
    public static final int frameWidthSize = 400;
    public static final int frameHeightSize = 600;
    public static final int BACKGROUNDSPEED = 1;
    public static final int PLAYERPLANESPEED = 10;
    public static final int ENEMYPLANESPEED = 5;
    public static final int PLAYERBULLETSPEED = 10;
    public static final int ENEMYBULLETSPEED = 10;
    BackGround backgroundImage;
    BackGround backgroundImage2;
    PlayerPlane playerPlane;
    EnemyPlane enemyPlaneDown;
    EnemyPlane enemyPlaneCross1;
    EnemyPlane enemyPlaneCross2;
    Island island1;
    Island island2;
    EnemyBullet enemyBullet;
    ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();
    private BufferedImage backBufferedImage;
    Thread thread;
    private Graphics backGraphics;
    //PlayerBullet playerBullet;
    ArrayList<PlayerBullet> playerBulletList = new ArrayList<PlayerBullet>();

    public GameWindow() {
        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);
        playerPlane = new PlayerPlane(frameWidthSize / 2 - 35, frameHeightSize - 50, "plane3.png", PLAYERPLANESPEED);
        enemyPlaneDown = new EnemyPlane(frameWidthSize / 2 - 32 / 2, 0,
                "enemy_plane_white_3.png", ENEMYPLANESPEED);

        enemyPlaneCross1 = new EnemyPlane(0, 0, "enemy-green-1.png", ENEMYPLANESPEED);
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
        backgroundImage = new BackGround("background.png", 0, 0,
                BACKGROUNDSPEED, frameWidthSize, frameHeightSize);
        backgroundImage2 = new BackGround("background.png", 0, -frameHeightSize,
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
                        PlayerBullet playerBullet = new PlayerBullet(playerPlane.x, playerPlane.y,
                                playerPlane.planeWidth, PLAYERBULLETSPEED, "bullet.png", 13, 32);
                        playerBulletList.add(playerBullet);
                        break;
                    case KeyEvent.VK_RIGHT:
                        playerPlane.moveRight();
                        break;
                    case KeyEvent.VK_LEFT:
                        playerPlane.moveLeft();
                        break;
                    case KeyEvent.VK_UP:
                        playerPlane.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        playerPlane.moveDown();
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
        if (enemyPlaneDown.y > frameHeightSize) {
            int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
            enemyPlaneDown = new EnemyPlane(randomX, 0, "enemy_plane_white_3.png", ENEMYPLANESPEED);
        }
        if (enemyPlaneDown.y == 0) {
            enemyBullet = new EnemyBullet("enemy_bullet.png", ENEMYBULLETSPEED,
                    enemyPlaneDown.image, enemyPlaneDown.x, enemyPlaneDown.y);
            enemyBulletList.add(enemyBullet);
        }
        if (enemyPlaneCross1.x % 100 == 0) {
            enemyBullet = new EnemyBullet("enemy_bullet.png", ENEMYBULLETSPEED / 2,
                    enemyPlaneCross1.image, enemyPlaneCross1.x, enemyPlaneCross1.y);
            enemyBulletList.add(enemyBullet);
        }
        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage.image, backgroundImage.x, backgroundImage.y,
                    backgroundImage.width, backgroundImage.height, null);
            backGraphics.drawImage(backgroundImage2.image, backgroundImage2.x, backgroundImage2.y,
                    backgroundImage2.width, backgroundImage2.height, null);
            backGraphics.drawImage(island2.image, island2.x, island2.y, null);
            backGraphics.drawImage(island1.image, island1.x, island1.y, null);
            backGraphics.drawImage(playerPlane.image, playerPlane.x, playerPlane.y,
                    playerPlane.image.getWidth(null), playerPlane.image.getHeight(null), null);
            backGraphics.drawImage(enemyPlaneDown.image, enemyPlaneDown.x, enemyPlaneDown.y,
                    enemyPlaneDown.planeWidth, enemyPlaneDown.planeHeight, null);
            backGraphics.drawImage(enemyPlaneCross1.image, enemyPlaneCross1.x, enemyPlaneCross1.y,
                    enemyPlaneCross1.planeWidth, enemyPlaneCross1.planeHeight, null);
            for (EnemyBullet temp : enemyBulletList) {
                backGraphics.drawImage(temp.image, temp.x, temp.y, null);

                temp.moveDown();
            }
            int hitPlaneDownOrCrossPlane=0;
            Iterator<PlayerBullet> iter = playerBulletList.iterator();
            while (iter.hasNext()) {
                PlayerBullet temp = iter.next();
                if(enemyPlaneDown.getHitByPlayerBullet(temp))
                {
                    iter.remove();
                    int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
                    enemyPlaneDown = new EnemyPlane(randomX, 0,
                            "enemy_plane_white_3.png", ENEMYPLANESPEED);
                }

                else if (enemyPlaneCross1.getHitByPlayerBullet(temp)) {

                    iter.remove();
                    enemyPlaneCross1=new EnemyPlane(0, 0, "enemy-green-1.png", ENEMYPLANESPEED);
                } else {

                    backGraphics.drawImage(temp.image, temp.x, temp.y, temp.bulletWidth, temp.bulletHeight, null);
                    temp.moveUp();
                }

            }
            enemyPlaneDown.moveDown();
            enemyPlaneCross1.moveCrossToRight();
            backgroundImage.moveDown();
            backgroundImage2.moveDown();
            island1.moveDown();
            island2.moveDown();
            graphics.drawImage(backBufferedImage, 0, 0, null);
        }

    }

    public static Image loadImageFromFile(String url) {
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/" + url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
