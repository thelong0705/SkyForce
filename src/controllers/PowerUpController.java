package controllers;

import com.company.GameWindow;
import com.company.Utils;
import models.GameModel;
import models.PlayerPlaneModel;
import models.PowerUpModel;
import views.GameView;
import views.PowerUpView;

/**
 * Created by Inpriron on 3/6/2017.
 */
public class PowerUpController extends GameController {
    public PowerUpController(GameModel model, GameView view) {
        super(model, view);
    }
    public PowerUpController(int x, int y )
    {
        this(new PowerUpModel(x,y,GameWindow.POWER_UP_WIDTH,GameWindow.POWER_UP_HEIGHT,GameWindow.POWER_UP_SPEED),
                new PowerUpView(Utils.loadImageFromFile("power-up.png")));
    }
    public void run()
    {
        if(model instanceof PowerUpModel)
        {
            PowerUpModel powerUpModel= (PowerUpModel) model;
            ((PowerUpModel) model).moveDown();
        }
    }

    @Override
    public void onContact(GameController gameController) {
        if(gameController instanceof PlayerPlaneController)
        {
            this.model.setExist(false);
        }
    }
}
