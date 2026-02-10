package org.example.engine;

import javax.swing.JFrame;

import org.example.engine.GameCanvas;

public class GameWindow extends JFrame {
    private final GameCanvas canvas;

    public GameWindow(int width, int height) {
        setTitle("2D Game Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new GameCanvas(width, height);
        add(canvas);
        pack();
        setLocationRelativeTo(null);
    }

    protected void render() {
        canvas.repaint();
    }
}
