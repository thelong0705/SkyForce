package views;

import com.company.Utils;
import models.EnemyPlaneModel;
import models.GameModel;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneView extends GameView{

    private int stateOfExplosion;

    public EnemyPlaneView(Image image) {
        super(image);
    }

    public int getStateOfExplosion() {
        return stateOfExplosion;
    }

    public void drawExplosion(Graphics g, GameModel model) {
        stateOfExplosion++;
        if (stateOfExplosion <= 6)
            image = Utils.loadImageFromFile("explosion" + stateOfExplosion + ".png");
        g.drawImage(image, model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
