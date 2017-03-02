package controllers;

import com.company.PlayerBullet;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Inpriron on 3/1/2017.
 */
public class ControllerManager {
    public Vector<GameController> gameControllerVector;

    public Vector<EnemyBulletController> enemyBulletControllerVector;
    public ControllerManager() {
        this.gameControllerVector = new Vector<>();
        enemyBulletControllerVector= new Vector<>();
    }

    public void draw(Graphics g) {
        for(GameController controller : gameControllerVector) {
            controller.draw(g);
        }
    }
    public void run() {
        for(GameController controller : gameControllerVector) {
            {
                if(controller instanceof PlayerBulletController)
                {
                    PlayerBulletController playerBulletController= (PlayerBulletController) controller;
                    playerBulletController.run();
                }
                if(controller instanceof EnemyPlaneController)
                {
                    EnemyPlaneController enemyPlaneController = (EnemyPlaneController) controller;
                    enemyPlaneController.run(enemyBulletControllerVector);
                }
//                if(controller instanceof EnemyBulletController)
//                {
//                    EnemyBulletController enemyBulletController= (EnemyBulletController) controller;
//                    enemyBulletController.run();
//                }
            }
        }
        for(EnemyBulletController enemyBulletController: enemyBulletControllerVector)
        {
            enemyBulletController.run();
        }
    }
    public void add(GameController controller) {
        gameControllerVector.add(controller);
    }

    public void delete(GameController controller) {
        gameControllerVector.remove(controller);
    }

}
