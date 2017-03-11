package controllers.strategy;

import com.company.GameWindow;
import com.company.Utils;
import controllers.EnemyBulletController;
import models.EnemyBulletModel;
import models.GameModel;
import views.EnemyBulletView;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class ShotStraightDown extends ShotBehavior {
    @Override
    public void shot(GameModel model) {
        super.shot(model);
        EnemyBulletController enemyBulletController= new EnemyBulletController(
                new EnemyBulletModel(x,y ,GameWindow.ENEMY_BULLET_WIDTH, GameWindow.ENEMY_BULLET_HEIGHT, GameWindow.ENEMY_BULLET_SPEED),
                new EnemyBulletView(Utils.loadImageFromFile("bullet-round.png")), EnemyBulletController.Type.BULLET);
        GameWindow.controllerManager.enemyBulletControllerVector.add(enemyBulletController);
    }


}
