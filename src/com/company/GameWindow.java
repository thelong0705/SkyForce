package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class GameWindow extends Frame {
    Image backgroundImage;
    Image planeImage;
    int frameWidthSize = 400;
    int frameHeightSize = 600;
    int planeX = frameWidthSize / 2 - 35;
    int planeY = frameHeightSize - 50;

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
        update(getGraphics());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                super.keyTyped(keyEvent);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (planeX + 10 > frameWidthSize - 70) ;
                        else
                            planeX += 10;
                        
                        update(getGraphics());
                        break;
                    case KeyEvent.VK_LEFT:
                        if (planeX - 10 < 0) ;
                        else
                            planeX -= 10;

                        update(getGraphics());
                        break;
                    case KeyEvent.VK_UP:
                        if (planeY - 10 < 30) ;
                        else
                            planeY -= 10;

                        update(getGraphics());
                        break;
                    case KeyEvent.VK_DOWN:
                        if (planeY + 10 > frameHeightSize - 50) ;
                        else
                            planeY += 10;

                        update(getGraphics());
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
            }
        });
    }

    @Override
    public void update(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, frameWidthSize, frameHeightSize, null);
        graphics.drawImage(planeImage, planeX, planeY, 70, 50, null);
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