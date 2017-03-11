package controllers;

import com.company.GameWindow;
import models.EnemyPlaneModel;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Inpriron on 3/1/2017.
 */
public class ControllerManager {
    public Vector<GameController> gameControllerVector;

    public Vector<EnemyBulletController> enemyBulletControllerVector;

    public Vector<GameController> gameControllerExplosionList;

    private int cycleCount=0;
    private int secondCounter=0;
    public ControllerManager() {
        this.gameControllerVector = new Vector<>();
        enemyBulletControllerVector = new Vector<>();
        gameControllerExplosionList = new Vector<>();
    }

    public void draw(Graphics g) {
        synchronized (gameControllerExplosionList)
        {
            for (GameController controller : gameControllerExplosionList)
                controller.drawExplosion(g);
        }



        for (GameController controller : gameControllerVector) {
            controller.draw(g);
        }

    }

    public void run() {
        spawnEnemies();
        Iterator<GameController> gameControllerIterator = gameControllerVector.iterator();
        while (gameControllerIterator.hasNext()) {
            GameController controller = gameControllerIterator.next();

            if (!controller.getModel().isExist())
                gameControllerIterator.remove();
            else
                controller.run();

        }
        Iterator<EnemyBulletController> iter = enemyBulletControllerVector.iterator();
        while (iter.hasNext()) {
            EnemyBulletController temp = iter.next();
            gameControllerVector.add(temp);
            iter.remove();
        }
        Iterator<GameController> iter1 = gameControllerExplosionList.iterator();
        {
            while (iter1.hasNext()) {
                GameController temp = iter1.next();
                if (temp.getModel().getStateOfExplosion() > 5)
                    iter1.remove();
            }
        }
    }

    public void checkOverLap() {
        int i;
        int j;
        for (i = 0; i < gameControllerVector.size() - 1; ++i) {
            for (j = i + 1; j < gameControllerVector.size(); ++j) {
                GameController controller1 = gameControllerVector.get(i);
                GameController controller2 = gameControllerVector.get(j);

                if (controller1.getModel().intersects(controller2.getModel())) {
                    controller1.onContact(controller2);
                    controller2.onContact(controller1);
                }
            }
        }
    }

    public void add(GameController controller) {
        gameControllerVector.add(controller);
    }

    public void spawnEnemies()
    {
        cycleCount++;
        if(cycleCount%60==0)
        {
            secondCounter++;
            if(secondCounter%2==0)
                this.add(EnemyPlaneController.create(EnemyPlaneModel.EnemyType.StraightDown));
            else if(secondCounter%2==1)
                this.add(EnemyPlaneController.create(EnemyPlaneModel.EnemyType.CrossRight));
        }
    }
}
