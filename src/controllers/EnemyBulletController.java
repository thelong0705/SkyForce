package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.EnemyBulletModel;
import models.GameModel;
import views.EnemyBulletView;
import views.GameView;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletController extends GameController {
    public enum Type {
        BULLET, MINE;
    }

    private Type type;

    public EnemyBulletController(GameModel model, GameView view, Type type) {
        super(model, view);
        this.type = type;
    }

//    public EnemyBulletController(int x, int y, Type type) {
//        this(new EnemyBulletModel(x, y, GameWindow.ENEMY_BULLET_WIDTH, GameWindow.ENEMY_BULLET_HEIGHT, GameWindow.ENEMY_BULLET_SPEED),
//                new EnemyBulletView(Utils.loadImageFromFile("bullet-round.png")), type);
//    }


    @Override
    public void run() {
        super.run();
        if (model instanceof EnemyBulletModel) {
            EnemyBulletModel enemyBulletModel = (EnemyBulletModel) model;
            if (type == Type.BULLET)
                enemyBulletModel.fly();
            else
                enemyBulletModel.flyToPlayerPlane();
        }

    }

    @Override
    public void onContact(GameController gameController) {
        if (gameController instanceof PlayerBulletController||gameController instanceof PlayerBombController) {
            this.model.setExist(false);
            GameWindow.controllerManager.gameControllerExplosionList.add(this);
        }


    }
}
