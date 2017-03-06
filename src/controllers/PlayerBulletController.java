package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.PlayerBulletModel;
import views.GameView;
import views.PlayerBulletView;

import javax.swing.*;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletController extends GameController {
    public enum TypeOfBullet {
        straightNormal, straightDouble, crossLeft, crossRight, bomb
    };
    TypeOfBullet typeOfBullet;

    public PlayerBulletController(PlayerBulletModel model, GameView view,TypeOfBullet typeOfBullet) {
        super(model, view);
        this.typeOfBullet=typeOfBullet;
        switch (typeOfBullet)
        {
            case straightNormal: this.view.setImage(Utils.loadImageFromFile("bullet.png"));break;
            case straightDouble: this.view.setImage(Utils.loadImageFromFile("bullet-double.png"));break;
            case crossLeft: this.view.setImage(Utils.loadImageFromFile("bullet-right.png"));break;
            case crossRight: this.view.setImage(Utils.loadImageFromFile("bullet-left.png"));break;

        }
    }

    public PlayerBulletController(int x, int y,TypeOfBullet typeOfBullet) {
                this( new PlayerBulletModel(x,y, GameWindow.PLAYER_BULLET_WIDTH,GameWindow.PLAYER_BULLET_HEIGHT,GameWindow.PLAYER_BULLET_SPEED),
                new PlayerBulletView(Utils.loadImageFromFile("bullet.png")),typeOfBullet);
    }
    public void run() {
        super.run();
        if (model instanceof PlayerBulletModel) {
            PlayerBulletModel bulletModel = (PlayerBulletModel) model;
            if(this.typeOfBullet==TypeOfBullet.straightNormal||this.typeOfBullet==TypeOfBullet.straightDouble)
                bulletModel.fly();
            else if(this.typeOfBullet==TypeOfBullet.crossLeft)
                bulletModel.moveCrossLeft();
            else
                bulletModel.moveCrossRight();
        }
    }

    @Override
    public void onContact(GameController gameController) {
        if(!(gameController instanceof PlayerBulletController)&&!(gameController instanceof PlayerPlaneController))
        {
            this.model.setExist(false);
            GameWindow.controllerManager.gameControllerExplosionList.add(this);
        }
    }
}
