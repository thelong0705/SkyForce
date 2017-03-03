package controllers;

import com.company.EnemyPlane;
import com.company.GameWindow;
import com.company.Utils;
import models.EnemyPlaneModel;
import models.GameModel;
import views.EnemyBulletView;
import views.EnemyPlaneView;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;
import java.util.ArrayList;
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
        this(new EnemyPlaneModel(x, y, GameWindow.ENEMYPLANEWIDTH, GameWindow.ENEMYPLANEHEIGHT,GameWindow.ENEMYPLANESPEED),
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
        if(model.getY()%200==0)
        {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMYPLANEWIDTH - GameWindow.ENEMYBULLETWIDTH) / 2,
                    model.getY() + GameWindow.ENEMYPLANEHEIGHT,EnemyBulletController.Type.BULLET);
            enemyBulletControllerVector.add(enemyBulletController);
        }

    }
    public void shootBombTowardPlayer(Vector<EnemyBulletController> enemyBulletControllerVector)
    {
        if(model.getY()%50==0)
        {
            EnemyBulletController enemyBulletController
                    = new EnemyBulletController(
                    model.getX() + (GameWindow.ENEMYPLANEWIDTH - GameWindow.ENEMYBULLETWIDTH) / 2,
                    model.getY() + GameWindow.ENEMYPLANEHEIGHT+50,EnemyBulletController.Type.BOMB);
            enemyBulletController.getModel().setWidth(GameWindow.ENEMYBULLETWIDTH*2);
            enemyBulletController.getModel().setHeight(GameWindow.ENEMYBULLETHEIGHT*2);
            enemyBulletController.getView().setImage(Utils.loadImageFromFile("bomb.png"));
            enemyBulletControllerVector.add(enemyBulletController);
        }
    }
}
