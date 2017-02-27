package controllers;

import com.company.GameWindow;
import com.company.PlayerBullet;
import com.company.Utils;
import models.PlayerBulletModel;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletController {
    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletModel getModel() {
        return model;
    }

    public PlayerBulletView getView() {
        return view;
    }

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;

    }

    public void run() {
        model.fly();
    }

    public PlayerBulletController(int x, int y) {
        this( new PlayerBulletModel(x,y, GameWindow.PLAYERBULLETWIDTH,GameWindow.PLAYERBULLETHEIGHT),
                new PlayerBulletView(Utils.loadImageFromFile("bullet.png")));
    }

    public void draw(Graphics graphics)
    {
        view.draw(graphics,model);
    }
}
