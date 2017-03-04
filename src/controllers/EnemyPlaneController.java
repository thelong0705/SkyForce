package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.EnemyPlaneModel;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneController extends GameController {

    public enum Type{
        moveDownEnemy, moveCrossEnemy
    }
    private Type type;
    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view,Type type) {
        super(model, view);
        this.type=type;

    }


    public EnemyPlaneController(int x, int y, Image image,Type type) {
        this(new EnemyPlaneModel(x, y, GameWindow.ENEMY_PLANE_WIDTH, GameWindow.ENEMY_PLANE_HEIGHT,GameWindow.ENEMY_PLANE_SPEED),
                new EnemyPlaneView(image),type);
    }

    public void run(Vector<EnemyBulletController> enemyBulletControllerVector)
    {
        if(type==Type.moveDownEnemy)
        {
            moveDown();
            shootBullet(enemyBulletControllerVector);
        }
        else
        {
            moveCrossToRight();
            shootBombTowardPlayer(enemyBulletControllerVector);
        }

    }
    public void moveDown() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveDown();
        }
    }

    public void moveCrossToRight(){
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveCrossToRight();
        }
    }

    public EnemyPlaneView getView()
    {
        if (view instanceof EnemyPlaneView) {
            EnemyPlaneView planeView = (EnemyPlaneView) view;
            return planeView;
        }
       return null;
    }
    public void shootBullet(Vector<EnemyBulletController> enemyBulletControllerVector) {
        if(model.getY()%150==0)
        {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT,EnemyBulletController.Type.BULLET);
            enemyBulletControllerVector.add(enemyBulletController);
        }

    }
    public void shootBombTowardPlayer(Vector<EnemyBulletController> enemyBulletControllerVector)
    {
        if(model.getY()%50==0)
        {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMY_PLANE_WIDTH - GameWindow.ENEMY_BULLET_WIDTH) / 2,
                    model.getY() + GameWindow.ENEMY_PLANE_HEIGHT +50,EnemyBulletController.Type.BOMB);
            enemyBulletController.getModel().setWidth(GameWindow.ENEMY_BULLET_WIDTH *2);
            enemyBulletController.getModel().setHeight(GameWindow.ENEMY_BULLET_HEIGHT *2);
            enemyBulletController.getView().setImage(Utils.loadImageFromFile("bomb.png"));
            enemyBulletControllerVector.add(enemyBulletController);
        }
    }
}
