package controllers;

import com.company.GameWindow;
import com.company.PlayerBullet;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Inpriron on 3/1/2017.
 */
public class ControllerManager {
    public Vector<GameController> gameControllerVector;

    public Vector<EnemyBulletController> enemyBulletControllerVector;

    public ControllerManager() {
        this.gameControllerVector = new Vector<>();
        enemyBulletControllerVector = new Vector<>();
    }

    public void draw(Graphics g) {
        for (GameController controller : gameControllerVector) {
            controller.draw(g);
        }
    }

    public void run() {

        Iterator<GameController> gameControllerIterator = gameControllerVector.iterator();
        {
            while (gameControllerIterator.hasNext()) {
                GameController controller = gameControllerIterator.next();
                if (controller.getModel().getY() > GameWindow.frameHeightSize || controller.getModel().getY() < 0)
                    gameControllerIterator.remove();
                else if(!controller.getModel().isExist())
                {
                    gameControllerIterator.remove();
                }
                else {
                    if (controller instanceof PlayerBulletController) {
                        PlayerBulletController playerBulletController = (PlayerBulletController) controller;
                        playerBulletController.run();
                    } else if (controller instanceof EnemyPlaneController) {
                        EnemyPlaneController enemyPlaneController = (EnemyPlaneController) controller;
                        enemyPlaneController.run(enemyBulletControllerVector);
                    } else if (controller instanceof EnemyBulletController) {
                        EnemyBulletController enemyBulletController = (EnemyBulletController) controller;
                        enemyBulletController.run();
                    }
                }
            }
        }
        Iterator<EnemyBulletController> iter = enemyBulletControllerVector.iterator();
        while (iter.hasNext()) {
            EnemyBulletController temp = iter.next();
            gameControllerVector.add(temp);
            iter.remove();
        }
    }

    public void checkOverLap()
    {
       int i;
       int j;
       for(i=0;i<gameControllerVector.size()-1;++i)
       {
           for(j=i+1;j<gameControllerVector.size();++j)
           {
               GameController controller1= gameControllerVector.get(i);
               GameController controller2= gameControllerVector.get(j);

               if(controller1.getModel().intersects(controller2.getModel()))
               {
                   controller1.getModel().setExist(false);
                   controller2.getModel().setExist(false);
               }
           }
       }
    }

    public void add(GameController controller) {
        gameControllerVector.add(controller);
    }

    public void delete(GameController controller) {
        gameControllerVector.remove(controller);
    }

}
