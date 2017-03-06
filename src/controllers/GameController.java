package controllers;

import com.company.GameWindow;
import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by Inpriron on 2/28/2017.
 */
public class GameController {

    protected GameModel model;
    protected GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public GameModel getModel() {
        return model;
    }



    public GameView getView() {
        return view;
    }


    public void draw(Graphics graphics) {

        view.draw(graphics, model);
    }

    public void drawExplosion(Graphics graphics) {
        view.drawExplosion(graphics,model);
    }

    public void run()
    {
        checkOutSideScreen();
    }

    public void onContact(GameController gameController)
    {

    }
    public void checkOutSideScreen()
    {
        int x= this.model.getX();
        int y= this.model.getY();
        if(x<0||x> GameWindow.frameWidthSize||y<0||y>GameWindow.frameHeightSize)
        {
            this.model.setExist(false);
        }
    }
}
