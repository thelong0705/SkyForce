package controllers;


import com.company.GameWindow;
import com.company.PlayerBullet;
import com.company.Utils;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import views.PlayerPlaneView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneController {
    private PlayerPlaneModel model;
    private PlayerPlaneView view;

    public PlayerPlaneModel getModel() {
        return model;
    }

    public PlayerPlaneView getView() {
        return view;
    }

    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerPlaneController(int x, int y) {
        this(new PlayerPlaneModel(x, y, GameWindow.PLAYERPLANESPEED, GameWindow.PLANEWIDTH, GameWindow.PLANEHEIGHT),
                new PlayerPlaneView(Utils.loadImageFromFile("plane3.png")));
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    public void moveUp() {
        model.moveUp();
    }

    public void moveRight() {
        model.moveRight();
    }

    public void moveLeft() {
        model.moveLeft();
    }

    public void moveDown() {
        model.moveDown();
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
