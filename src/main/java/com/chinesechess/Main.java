package com.chinesechess;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Chinese Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new StartMenu());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        



        // Game game = new Game();
        // game.start();
    }


}
