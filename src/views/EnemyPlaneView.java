package views;

import com.company.Utils;
import models.EnemyPlaneModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneView {
    private Image image;
    private int stateOfExplosion;
    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public int getStateOfExplosion() {
        return stateOfExplosion;
    }

    public void draw(Graphics g, EnemyPlaneModel model) {
        g.drawImage(image, model.getX(),

                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
    public void drawExplosion(Graphics g, EnemyPlaneModel model) {
        stateOfExplosion++;
        image=Utils.loadImageFromFile("explosion"+stateOfExplosion+".png");
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
