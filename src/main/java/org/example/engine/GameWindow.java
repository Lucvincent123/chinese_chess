package org.example.engine;

import javax.swing.JFrame;

import org.example.engine.GameCanvas;

public class GameWindow extends JFrame {
    private final GameCanvas canvas;

    public GameWindow(int width, int height, int maxNumberOfEntities) {
        setTitle("2D Game Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new GameCanvas(width, height, maxNumberOfEntities);
        add(canvas);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void render() {
        canvas.repaint();
    }


    public GameCanvas getCanvas() { return canvas; }

    public void setTitle(String title) {
        super.setTitle(title);
    }
}
