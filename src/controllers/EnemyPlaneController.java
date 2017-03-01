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

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneController extends GameController {
    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        super(model, view);
    }


    public EnemyPlaneController(int x, int y, Image image) {
        this(new EnemyPlaneModel(x, y, GameWindow.ENEMYPLANESPEED, GameWindow.ENEMYPLANEWIDTH, GameWindow.ENEMYPLANEHEIGHT),
                new EnemyPlaneView(image));
    }


    public void moveUp() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveUp();
        }
    }

    public void moveRight() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveRight();
        }
    }

    public void moveLeft() {
        if (model instanceof EnemyPlaneModel) {
            EnemyPlaneModel planeModel = (EnemyPlaneModel) model;
            planeModel.moveLeft();
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
    public ArrayList<EnemyBulletController> shootBullet(ArrayList<EnemyBulletController> enemyBulletControllerList) {
        EnemyBulletController enemyBulletController
                = new EnemyBulletController(
                model.getX() + (GameWindow.ENEMYPLANEWIDTH - GameWindow.ENEMYBULLETWIDTH) / 2,
                model.getY() + GameWindow.ENEMYPLANEHEIGHT);
        enemyBulletControllerList.add(enemyBulletController);
        return enemyBulletControllerList;
    }
}
