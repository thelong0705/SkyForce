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
    public static int frameWidthSize = 400;
    public static int frameHeightSize = 600;

    PlayerPlane playerPlane;
    EnemyPlane enemyPlaneDown;
    EnemyPlane enemyPlaneCross1;
    EnemyPlane enemyPlaneCross2;
    private BufferedImage backBufferedImage;
    Thread thread;
    private Graphics backGraphics;
    //PlayerBullet playerBullet;
    ArrayList<PlayerBullet> playerBulletList = new ArrayList<PlayerBullet>();

    public GameWindow() {
        setVisible(true);
        setSize(frameWidthSize, frameHeightSize);
        playerPlane = new PlayerPlane(70, 50, "plane3.png", 10);
        enemyPlaneDown = new EnemyPlane(frameWidthSize / 2 - 16, 30,
                32, 32, "enemy_plane_white_2.png", 5);
        enemyPlaneCross1 = new EnemyPlane(0, 0, 32, 32, "enemy_plane_white_3.png", 5);
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
        backgroundImage = loadImageFromFile("background.png");
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
                                playerPlane.planeWidth, 10, "bullet.png", 13, 32);
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
        if (backBufferedImage != null) {
            backGraphics = backBufferedImage.getGraphics();
            backGraphics.drawImage(backgroundImage, 0, 0, frameWidthSize, frameHeightSize, null);
            backGraphics.drawImage(playerPlane.image, playerPlane.x, playerPlane.y,
                    playerPlane.planeWidth, playerPlane.planeHeight, null);
            backGraphics.drawImage(enemyPlaneDown.image, enemyPlaneDown.x, enemyPlaneDown.y,
                    enemyPlaneDown.planeWidth, enemyPlaneDown.planeHeight, null);
            backGraphics.drawImage(enemyPlaneCross1.image, enemyPlaneCross1.x, enemyPlaneCross1.y,
                    enemyPlaneCross1.planeWidth, enemyPlaneCross1.planeHeight, null);
            enemyPlaneDown.moveDown();
            enemyPlaneCross1.moveCrossToRight();
            if (enemyPlaneDown.y > frameHeightSize) {
                enemyPlaneDown = new EnemyPlane(frameWidthSize / 2 - 16, 30,
                        32, 32, "enemy_plane_white_2.png", 5);
            }
            if (enemyPlaneCross1.x > frameWidthSize)
                enemyPlaneCross1 = new EnemyPlane(0, 0, 32, 32, "enemy_plane_white_3.png", 5);
            for (PlayerBullet temp : playerBulletList) {

                backGraphics.drawImage(temp.image, temp.x, temp.y, temp.bulletWidth, temp.bulletHeight, null);
                temp.moveUp();
            }

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