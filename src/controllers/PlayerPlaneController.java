package controllers;


import com.company.GameWindow;
import com.company.PlayerBullet;
import com.company.Utils;
import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import views.GameView;
import views.PlayerPlaneView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneController extends GameController {
    private Vector<GameController> playerBulletControllers;
    public PlayerPlaneController(PlayerPlaneModel model, GameView view,Vector<GameController> playerBulletControllers) {
        super(model, view);
        this.playerBulletControllers= playerBulletControllers;
    }

    public PlayerPlaneController(int x, int y,Vector<GameController> playerBulletControllers) {
        this(new PlayerPlaneModel(x, y, GameWindow.PLANEWIDTH, GameWindow.PLANEHEIGHT,GameWindow.PLAYERPLANESPEED),
                new PlayerPlaneView(Utils.loadImageFromFile("plane3.png")),playerBulletControllers);
    }


    public void moveUp() {
        if (model instanceof PlayerPlaneModel) {
            PlayerPlaneModel planeModel = (PlayerPlaneModel) model;
            planeModel.moveUp();
        }
    }

    public void moveRight() {
        if (model instanceof PlayerPlaneModel) {
            PlayerPlaneModel planeModel = (PlayerPlaneModel) model;
            planeModel.moveRight();
        }
    }

    public void moveLeft() {
        if (model instanceof PlayerPlaneModel) {
            PlayerPlaneModel planeModel = (PlayerPlaneModel) model;
            planeModel.moveLeft();
        }
    }

    public void moveDown() {
        if (model instanceof PlayerPlaneModel) {
            PlayerPlaneModel planeModel = (PlayerPlaneModel) model;
            planeModel.moveDown();
        }
    }


//    public ArrayList<PlayerBulletController> shootBullet(ArrayList<PlayerBulletController> playerBulletControllerList) {
//        PlayerBulletController playerBulletController
//                = new PlayerBulletController(
//                model.getX() + (GameWindow.PLANEWIDTH - GameWindow.PLAYERBULLETWIDTH) / 2,
//                model.getY() - GameWindow.PLAYERBULLETHEIGHT);
//        playerBulletControllerList.add(playerBulletController);
//        return playerBulletControllerList;
//    }
    public void shoot()
    {
        PlayerBulletController playerBulletController= new PlayerBulletController((int)model.getMidX()-GameWindow.PLAYERBULLETWIDTH/2,
                model.getY()-GameWindow.PLAYERBULLETHEIGHT);
        playerBulletControllers.add(playerBulletController);
    }
}
