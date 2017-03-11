package controllers.strategy;

import com.company.GameWindow;
import models.GameModel;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class ShotBehavior {
    protected  int x;
    protected  int y;
    public void shot(GameModel model) {
         x= model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2;
         y=model.getY() + GameWindow.ENEMY_PLANE_HEIGHT;
    }
}
