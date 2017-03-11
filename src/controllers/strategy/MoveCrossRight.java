package controllers.strategy;

import com.company.GameWindow;
import models.GameModel;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class MoveCrossRight extends MoveBehavior {
    @Override
    public void move(GameModel model) {
        model.moveCrossToRight();
    }
}
