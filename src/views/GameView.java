package views;

import com.company.Utils;
import models.GameModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/28/2017.
 */
public class GameView {
    public void setImage(Image image) {
        this.image = image;
    }

    protected Image image;

    public GameView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, GameModel model) {
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
    public void drawExplosion(Graphics g, GameModel model) {
        model.setStateOfExplosion(model.getStateOfExplosion()+1);
        if (model.getStateOfExplosion()<=6)
            image = Utils.loadImageFromFile("explosion" + model.getStateOfExplosion() + ".png");
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}

