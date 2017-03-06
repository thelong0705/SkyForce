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
    public enum TypeOfShoot {
        shootNormal, shootDouble, shootTriple, shootBomb
    }

    ;


    private TypeOfShoot typeOfShoot = TypeOfShoot.shootNormal;
    public static final PlayerPlaneController instance = new PlayerPlaneController(GameWindow.frameWidthSize / 2 - GameWindow.PLANE_WIDTH / 2,
            GameWindow.frameHeightSize - GameWindow.PLANE_HEIGHT);

    public PlayerPlaneController(PlayerPlaneModel model, GameView view) {
        super(model, view);
    }

    public PlayerPlaneController(int x, int y) {
        this(new PlayerPlaneModel(x, y, GameWindow.PLANE_WIDTH, GameWindow.PLANE_HEIGHT, GameWindow.PLAYER_PLANE_SPEED),
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

    public void shoot() {
        PlayerBulletController playerBulletController = null;
        switch (typeOfShoot) {
            case shootNormal:
                playerBulletController = new PlayerBulletController((int) model.getMidX() - GameWindow.PLAYER_BULLET_WIDTH / 2,
                        model.getY() - GameWindow.PLAYER_BULLET_HEIGHT, PlayerBulletController.TypeOfBullet.straightNormal);
                break;
            case shootDouble:
                playerBulletController = new PlayerBulletController((int) model.getMidX() - GameWindow.PLAYER_BULLET_WIDTH / 2,
                        model.getY() - GameWindow.PLAYER_BULLET_HEIGHT, PlayerBulletController.TypeOfBullet.straightDouble);
                break;
            case shootTriple:
                playerBulletController = new PlayerBulletController((int) model.getMidX() - GameWindow.PLAYER_BULLET_WIDTH / 2,
                        model.getY() - GameWindow.PLAYER_BULLET_HEIGHT, PlayerBulletController.TypeOfBullet.straightDouble);
                PlayerBulletController playerBulletControllerLeft = new PlayerBulletController((int) model.getMidX() - GameWindow.PLAYER_BULLET_WIDTH / 2,
                        model.getY() - GameWindow.PLAYER_BULLET_HEIGHT, PlayerBulletController.TypeOfBullet.crossLeft);
                PlayerBulletController playerBulletControllerRight = new PlayerBulletController((int) model.getMidX() - GameWindow.PLAYER_BULLET_WIDTH / 2,
                        model.getY() - GameWindow.PLAYER_BULLET_HEIGHT, PlayerBulletController.TypeOfBullet.crossRight);
                GameWindow.controllerManager.add(playerBulletControllerLeft);
                GameWindow.controllerManager.add(playerBulletControllerRight);
                break;

        }
        if (playerBulletController != null)
            GameWindow.controllerManager.add(playerBulletController);

    }

    @Override
    public void onContact(GameController gameController) {
        if (gameController instanceof PowerUpController) {
            upgradeBullet();
        }
    }

    private void upgradeBullet() {
        switch (typeOfShoot) {
            case shootNormal:
                typeOfShoot = TypeOfShoot.shootDouble;
                break;
            case shootDouble:
                typeOfShoot = TypeOfShoot.shootTriple;
                break;
            case shootTriple:

                break;
        }
    }
}
