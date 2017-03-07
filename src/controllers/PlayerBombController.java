package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.GameModel;
import models.PlayerBombModel;
import models.PlayerBulletModel;
import views.GameView;
import views.PlayerBombView;
import views.PlayerBulletView;

/**
 * Created by Inpriron on 3/7/2017.
 */
public class PlayerBombController extends GameController {
    public PlayerBombController(GameModel model, GameView view) {
        super(model, view);
    }

    public PlayerBombController(int x, int y) {
        this(new PlayerBombModel(x, y, GameWindow.PLAYER_BULLET_WIDTH * 2, GameWindow.PLAYER_BULLET_HEIGHT * 2, GameWindow.PLAYER_BULLET_SPEED),
                new PlayerBombView(Utils.loadImageFromFile("bomb.png")));
    }

    @Override
    public void run() {
        super.run();
        if (model instanceof PlayerBombModel) {
            PlayerBombModel playerBombModel = (PlayerBombModel) model;
            playerBombModel.moveUp();
        }
    }

    @Override
    public void onContact(GameController gameController) {

            if(!(gameController instanceof PlayerPlaneController)&&!(gameController instanceof PlayerBombController)&&
                    !(gameController instanceof PowerUpController)){

                    this.model.setExist(false);
                    GameWindow.controllerManager.gameControllerExplosionList.add(this);

            }
        }
}

