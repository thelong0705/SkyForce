package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.EnemyPlaneModel;
import models.GameModel;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneController extends GameController {

    public enum Type {
        moveDownEnemy, moveCrossEnemy
    }

    private Type type;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view, Type type) {
        super(model, view);
        this.type = type;

    }

    public EnemyPlaneController(int x, int y, Image image, Type type) {
        this(new EnemyPlaneModel(x, y, GameWindow.ENEMY_PLANE_WIDTH, GameWindow.ENEMY_PLANE_HEIGHT, GameWindow.ENEMY_PLANE_SPEED),
                new EnemyPlaneView(image), type);
    }

    @Override
    public void run() {
        super.run();
        if (type == Type.moveDownEnemy) {
            moveDown();
            shootBullet();
        } else {
            moveCrossToRight();
            shootBombTowardPlayer();
        }
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

    public void shootBullet() {
        if (model.getY() % 150 == 0) {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT, EnemyBulletController.Type.BULLET);
            GameWindow.controllerManager.enemyBulletControllerVector.add(enemyBulletController);
        }

    }

    public void shootBombTowardPlayer() {
        if (model.getY() % 50 == 0) {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT , EnemyBulletController.Type.BOMB);
            enemyBulletController.getModel().setWidth(GameWindow.ENEMY_BULLET_WIDTH * 2);
            enemyBulletController.getModel().setHeight(GameWindow.ENEMY_BULLET_HEIGHT * 2);
            enemyBulletController.getView().setImage(Utils.loadImageFromFile("mine.png"));
            GameWindow.controllerManager.enemyBulletControllerVector.add(enemyBulletController);
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
