package views;

import com.company.Utils;
import models.GameModel;

import java.awt.*;

/**
 * Created by Inpriron on 3/7/2017.
 */
public class PlayerBombView extends GameView {
    public PlayerBombView(Image image) {
        super(image);
    }

    @Override
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
