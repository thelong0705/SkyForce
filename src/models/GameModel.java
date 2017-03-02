package models;

/**
 * Created by Inpriron on 2/28/2017.
 */
public class GameModel {
    protected  int x;
    protected  int y;
    protected  int speed;
    protected  int width;
    protected  int height;


    public GameModel(int x, int y, int width, int height,int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMidX()
    {
        return x+width/2.0;
    }


}
