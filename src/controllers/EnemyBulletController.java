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
    public enum Type{
       BULLET,BOMB;
    }
    private Type type;
    public EnemyBulletController(GameModel model, GameView view,Type type ) {
        super(model, view);
        this.type=type;
    }

    public EnemyBulletController(int x, int y,Type type) {
        this( new EnemyBulletModel(x,y,GameWindow.ENEMYBULLETWIDTH,GameWindow.ENEMYBULLETHEIGHT,GameWindow.ENEMYBULLETSPEED),
                new EnemyBulletView(Utils.loadImageFromFile("bullet-round.png")),type);
    }

    public void run() {
        if(model instanceof EnemyBulletModel)
        {
            EnemyBulletModel enemyBulletModel= (EnemyBulletModel) model;
            if(type==Type.BULLET)
                enemyBulletModel.fly();
            else
                enemyBulletModel.flyToPlayerPlane();
        }
    }
}
