package org.example.chinesechess;


import org.example.engine.GameWindow;

public class Main {
    public static void main(String[] args) {
        GameWindow window = new GameWindow(800, 600, 100);
        window.setVisible(true);
        Container container = new Container(window, 60, 60);
        container.start();
    }
}
