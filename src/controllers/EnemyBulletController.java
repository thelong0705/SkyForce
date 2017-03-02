package controllers;

import com.company.EnemyBullet;
import com.company.GameWindow;
import com.company.Utils;
import models.EnemyBulletModel;
import models.GameModel;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletController extends GameController {
    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
    }

    public EnemyBulletController(int x, int y) {
        this( new EnemyBulletModel(x,y,GameWindow.ENEMYBULLETWIDTH,GameWindow.ENEMYBULLETHEIGHT,GameWindow.ENEMYBULLETSPEED),
                new EnemyBulletView(Utils.loadImageFromFile("bullet-round.png")));
    }

    public void run() {
        if(model instanceof EnemyBulletModel)
        {
            EnemyBulletModel enemyBulletModel= (EnemyBulletModel) model;
            enemyBulletModel.fly();
        }
    }
}
