package org.example.engine;

import javax.swing.JPanel;
import java.awt.*;

public class GameCanvas extends JPanel {
    private DrawableEntity[] entities;
    private int lastEntityIndex = -1;

    public GameCanvas(int width, int height, int maxNumberOfEntities) {
        entities = new DrawableEntity[maxNumberOfEntities];
        this.setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
    }

    public void addEntity(DrawableEntity entity) {
        if (lastEntityIndex < entities.length - 1) {
            entities[++lastEntityIndex] = entity;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Render entity on the canvas
        for (int i = 0; i <= lastEntityIndex; i++) {
            DrawableEntity entity = entities[i];
            if (entity != null) {
                entity.draw(g);
            }
        }
    }

}
