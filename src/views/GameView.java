package views;

import models.GameModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/28/2017.
 */
public class GameView {
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
}
