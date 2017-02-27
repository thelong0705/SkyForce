package controllers;

import com.company.EnemyPlane;
import com.company.GameWindow;
import com.company.Utils;
import models.EnemyPlaneModel;
import views.EnemyPlaneView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by Inpriron on 2/27/2017.
 */
public class EnemyPlaneController {

    private EnemyPlaneModel model;
    private EnemyPlaneView view;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyPlaneController(int x, int y, Image image) {
        this(new EnemyPlaneModel(x, y, GameWindow.ENEMYPLANESPEED, GameWindow.ENEMYPLANEWIDTH, GameWindow.ENEMYPLANEHEIGHT),
                new EnemyPlaneView(image));
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }

    public void moveUp() {
        model.moveUp();
    }

    public void moveRight() {
        model.moveRight();
    }

    public void moveLeft() {
        model.moveLeft();
    }

    public void moveDown() {
        model.moveDown();
    }
    public void moveCrossToRight(){
        model.moveCrossToRight();
    }
}
