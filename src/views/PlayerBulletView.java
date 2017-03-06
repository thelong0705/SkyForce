package views;

import com.company.Utils;
import controllers.PlayerPlaneController;
import models.GameModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletView extends GameView {

    public PlayerBulletView(Image image) {
        super(image);

    }


    public void drawExplosion(Graphics g, GameModel model) {
        model.setStateOfExplosion(model.getStateOfExplosion()+1);
        if (model.getStateOfExplosion()<=6)
            image = Utils.loadImageFromFile("BulletExplosion" + model.getStateOfExplosion() + ".png");
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
