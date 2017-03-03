package models;

import com.company.GameWindow;
import controllers.PlayerPlaneController;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel{


    public EnemyBulletModel(int x, int y, int width, int height,int speed ) {

        super(x, y,width,height, speed);
    }

    public void fly()
    {
        y+=speed;
    }
    public void flyToPlayerPlane()
    {
        int dx= PlayerPlaneController.instance.getModel().getX()-x;
        int dy=PlayerPlaneController.instance.getModel().getY()-y;
        double distance= Math.sqrt(dx*dx+dy*dy);
        double speedX= speed*(dx/distance);
        double speedY= speed*(dy/distance);
        x+=speedX;
        y+=speedY;
    }
}
