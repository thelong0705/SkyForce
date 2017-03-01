package controllers;

import com.company.GameWindow;
import com.company.PlayerBullet;
import com.company.Utils;
import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletController extends GameController {

    public PlayerBulletController(PlayerBulletModel model, GameView view) {
        super(model, view);
    }

        public PlayerBulletController(int x, int y) {
        this( new PlayerBulletModel(x,y, GameWindow.PLAYERBULLETSPEED,GameWindow.PLAYERBULLETWIDTH,GameWindow.PLAYERBULLETHEIGHT),
                new PlayerBulletView(Utils.loadImageFromFile("bullet.png")));
    }
    public void run() {
        if (model instanceof PlayerBulletModel) {
            PlayerBulletModel bulletModel = (PlayerBulletModel) model;
            bulletModel.fly();
        }

    }




}
