package controllers.strategy;

import models.GameModel;

/**
 * Created by Inpriron on 3/11/2017.
 */
public class MoveStraightDown extends MoveBehavior{
    @Override
    public void move(GameModel model) {
       model.move(0,model.getSpeed());
    }
}
