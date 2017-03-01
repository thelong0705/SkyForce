package controllers;

import com.company.EnemyBullet;
import com.company.GameWindow;
import com.company.Utils;
import models.EnemyBulletModel;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletController {
    private EnemyBulletModel model;
    private EnemyBulletView view;

    public EnemyBulletModel getModel() {
        return model;
    }

    public EnemyBulletView getView() {
        return view;
    }

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        model.fly();
    }

    public EnemyBulletController(int x, int y) {
        this( new EnemyBulletModel(x,y,GameWindow.ENEMYBULLETSPEED, GameWindow.ENEMYBULLETWIDTH,GameWindow.ENEMYBULLETHEIGHT),
                new EnemyBulletView(Utils.loadImageFromFile("bullet-round.png")));
    }

    public void draw(Graphics graphics)
    {
        view.draw(graphics,model);
    }
}
