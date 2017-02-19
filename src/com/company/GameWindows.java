package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Inpriron on 2/19/2017.
 */
public class GameWindows extends Frame {
    Image backgroundImage;
    Image planeImage;
    Image islandImage;
    Image bulletImage;
    public GameWindows() {
        setVisible(true);
        setSize(400, 600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.out.println("WindowsClosing");
                System.exit(0);
            }
        });
//        try {
//            backgroundImage = ImageIO.read(new File("resources/background.png"));
//            planeImage = ImageIO.read(new File("resources/plane3.png"));
//            islandImage=ImageIO.read(new File("resources/island.png"));
//            bulletImage=ImageIO.read(new File("resources/bullet.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        backgroundImage= loadImage("background.png");
        planeImage=loadImage("plane3.png");
        bulletImage=loadImage("bullet.png");
        update(getGraphics());
    }
    private Image loadImage(String url) {
        try {
            Image image= ImageIO.read(new File("resources/"+url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, 400, 600, null);
        g.drawImage(planeImage, 150, 500, 70, 51, null);
        g.drawImage(bulletImage,180,250,7,17,null);

    }
}
