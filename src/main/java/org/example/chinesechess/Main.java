package org.example.chinesechess;


import org.example.engine.GameWindow;

import javax.swing.JFrame;

public class Main {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int MAX_ENTITIES = 100;

    private static final String GAME_TITLE = "Chinese Chess";
    private static final int TARGET_FPS = 60;
    private static final int TARGET_UPS = 60;


    public static void main(String[] args) {
        GameWindow window = new GameWindow(WINDOW_WIDTH, WINDOW_HEIGHT, MAX_ENTITIES);
        window.setTitle(GAME_TITLE);
        Container container = new Container(window, TARGET_FPS, TARGET_UPS);
        container.start();
    }
}
