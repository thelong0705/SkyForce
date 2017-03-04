package controllers;


import com.company.GameWindow;
import com.company.Utils;
import models.PlayerPlaneModel;
import views.GameView;
import views.PlayerPlaneView;

import java.util.Vector;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class PlayerPlaneController extends GameController {
    public static final PlayerPlaneController instance =   new PlayerPlaneController(GameWindow.frameWidthSize / 2 - GameWindow.PLANE_WIDTH / 2,
            GameWindow.frameHeightSize - GameWindow.PLANE_HEIGHT, GameWindow.controllerManager.gameControllerVector);;
    private Vector<GameController> playerBulletControllers;
    public PlayerPlaneController(PlayerPlaneModel model, GameView view,Vector<GameController> playerBulletControllers) {
        super(model, view);
        this.playerBulletControllers= playerBulletControllers;
    }

    public PlayerPlaneController(int x, int y,Vector<GameController> playerBulletControllers) {
        this(new PlayerPlaneModel(x, y, GameWindow.PLANE_WIDTH, GameWindow.PLANE_HEIGHT,GameWindow.PLAYER_PLANE_SPEED),
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
//                model.getX() + (GameWindow.PLANE_WIDTH - GameWindow.PLAYER_BULLET_WIDTH) / 2,
//                model.getY() - GameWindow.PLAYER_BULLET_HEIGHT);
//        playerBulletControllerList.add(playerBulletController);
//        return playerBulletControllerList;
//    }
    public void shoot()
    {
        PlayerBulletController playerBulletController= new PlayerBulletController((int)model.getMidX()-GameWindow.PLAYER_BULLET_WIDTH /2,
                model.getY()-GameWindow.PLAYER_BULLET_HEIGHT);
        playerBulletControllers.add(playerBulletController);
    }
}
