package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.PlayerBulletModel;
import views.GameView;
import views.PlayerBulletView;

/**
 * Created by Inpriron on 2/26/2017.
 */
public class PlayerBulletController extends GameController {

    public PlayerBulletController(PlayerBulletModel model, GameView view) {
        super(model, view);
    }

        public PlayerBulletController(int x, int y) {
        this( new PlayerBulletModel(x,y, GameWindow.PLAYER_BULLET_WIDTH,GameWindow.PLAYER_BULLET_HEIGHT,GameWindow.PLAYER_BULLET_SPEED),
                new PlayerBulletView(Utils.loadImageFromFile("bullet.png")));
    }
    public void run() {
        if (model instanceof PlayerBulletModel) {
            PlayerBulletModel bulletModel = (PlayerBulletModel) model;
            bulletModel.fly();
        }
    }

    @Override
    public void onContact(GameController gameController) {
        if(!(gameController instanceof PlayerBulletController))
            this.model.setExist(false);
    }
}
