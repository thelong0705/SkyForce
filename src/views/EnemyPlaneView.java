package views;

import models.EnemyPlaneModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneView {
    private Image image;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }
    public void draw(Graphics g, EnemyPlaneModel model) {
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
