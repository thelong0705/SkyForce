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

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneController extends GameController {
    public PlayerPlaneController(PlayerPlaneModel model, GameView view) {
        super(model, view);
    }

    public PlayerPlaneController(int x, int y) {
        this(new PlayerPlaneModel(x, y, GameWindow.PLAYERPLANESPEED, GameWindow.PLANEWIDTH, GameWindow.PLANEHEIGHT),
                new PlayerPlaneView(Utils.loadImageFromFile("plane3.png")));
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


    public ArrayList<PlayerBulletController> shootBullet(ArrayList<PlayerBulletController> playerBulletControllerList) {
        PlayerBulletController playerBulletController
                = new PlayerBulletController(
                model.getX() + (GameWindow.PLANEWIDTH - GameWindow.PLAYERBULLETWIDTH) / 2,
                model.getY() - GameWindow.PLAYERBULLETHEIGHT);
        playerBulletControllerList.add(playerBulletController);
        return playerBulletControllerList;
    }
}
