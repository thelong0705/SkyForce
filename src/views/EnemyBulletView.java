package views;

import com.company.Utils;
import models.EnemyBulletModel;
import models.GameModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletView extends GameView {

    public EnemyBulletView(Image image) {
        super(image);
    }

    @Override
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
