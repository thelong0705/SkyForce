package controllers;

import com.company.GameWindow;
import com.company.Utils;
import controllers.strategy.*;
import models.EnemyPlaneModel;
import views.EnemyPlaneView;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneController extends GameController {


    private MoveBehavior moveBehavior;
    private ShotBehavior shotBehavior;
    private int timerCount = 0;

    public ShotBehavior getShotBehavior() {
        return shotBehavior;
    }

    public void setShotBehavior(ShotBehavior shotBehavior) {
        this.shotBehavior = shotBehavior;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }


    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        super(model, view);

    }

//    public EnemyPlaneController(int x, int y) {
//        this(new EnemyPlaneModel(x, y, GameWindow.ENEMY_PLANE_WIDTH, GameWindow.ENEMY_PLANE_HEIGHT, GameWindow.ENEMY_PLANE_SPEED),
//                new EnemyPlaneView(Utils.loadImageFromFile("enemy_plane_white_3.png")));
//    }

    @Override
    public void run() {
        super.run();
        moveBehavior.move(this.model);

        if (timerCount % 40 == 0) {
            if (shotBehavior != null)
                shotBehavior.shot(this.model);

        }
        timerCount++;
    }

    public void moveDown() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveDown();
        }
    }

    public void moveCrossToRight() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveCrossToRight();
        }
    }

    public EnemyPlaneView getView() {
        if (view instanceof EnemyPlaneView) {
            EnemyPlaneView planeView = (EnemyPlaneView) view;
            return planeView;
        }
        return null;
    }

//    public void shootBullet() {
//        if (model.getY() % 150 == 0) {
//            EnemyBulletController enemyBulletController
//                    = new EnemyBulletController(
//                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
//                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT, EnemyBulletController.Type.BULLET);
//            GameWindow.controllerManager.enemyBulletControllerVector.add(enemyBulletController);
//        }
//
//    }

//    public void shootBombTowardPlayer() {
//        if (model.getY() % 50 == 0) {
//            EnemyBulletController enemyBulletController
//                    = new EnemyBulletController(
//                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
//                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT, EnemyBulletController.Type.MINE);
//            enemyBulletController.getModel().setWidth(GameWindow.ENEMY_BULLET_WIDTH * 2);
//            enemyBulletController.getModel().setHeight(GameWindow.ENEMY_BULLET_HEIGHT * 2);
//            enemyBulletController.getView().setImage(Utils.loadImageFromFile("mine.png"));
//            GameWindow.controllerManager.enemyBulletControllerVector.add(enemyBulletController);
//        }
//    }
//

    public static EnemyPlaneController create(EnemyPlaneModel.EnemyType enemyType) {
        int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
        EnemyPlaneController enemyPlaneController = null;
        switch (enemyType) {
            case StraightDown:
                enemyPlaneController = new EnemyPlaneController(
                        new EnemyPlaneModel(randomX, 0, GameWindow.ENEMY_PLANE_WIDTH, GameWindow.ENEMY_PLANE_HEIGHT, GameWindow.ENEMY_PLANE_SPEED),
                        new EnemyPlaneView(Utils.loadImageFromFile("enemy_plane_white_3.png")));
                enemyPlaneController.setMoveBehavior(new MoveStraightDown());
                enemyPlaneController.setShotBehavior(new ShotStraightDown());
                break;
            case CrossRight:
                enemyPlaneController = new EnemyPlaneController(
                        new EnemyPlaneModel(0, 0, GameWindow.ENEMY_PLANE_WIDTH, GameWindow.ENEMY_PLANE_HEIGHT, GameWindow.ENEMY_PLANE_SPEED),
                        new EnemyPlaneView(Utils.loadImageFromFile("enemy-green-1.png")));
                enemyPlaneController.setMoveBehavior(new MoveCrossRight());
                enemyPlaneController.setShotBehavior(new ShotFollowPlayer());
        }
        return enemyPlaneController;
    }

    @Override
    public void onContact(GameController gameController) {
        if (gameController instanceof PlayerBulletController || gameController instanceof PlayerBombController) {
            this.model.setExist(false);
            GameWindow.controllerManager.gameControllerExplosionList.add(this);
        }
    }

}
