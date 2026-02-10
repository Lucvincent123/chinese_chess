package org.example.engine;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameCanvas extends JPanel {

    public GameCanvas(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Here you would add your game rendering code

    }

}
