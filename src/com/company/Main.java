package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
	// write your code her
        int randomX = ThreadLocalRandom.current().nextInt(50, GameWindow.frameWidthSize);
    GameWindow gameWindow = new GameWindow();
    gameWindow.start();
    }
}
