package org.example.engine;

import javax.swing.JPanel;
import java.awt.*;

public class GameCanvas extends JPanel {
    private GameEntity[] entities;
    private int lastEntityIndex = -1;

    public GameCanvas(int width, int height, int maxNumberOfEntities) {
        entities = new GameEntity[maxNumberOfEntities];
        this.setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
    }

    public boolean addEntity(GameEntity entity) {
        if (lastEntityIndex < entities.length - 1) {
            entities[++lastEntityIndex] = entity;
            return true;
        }
        return false; // No more space for new entities
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Render entity on the canvas
        for (int i = 0; i <= lastEntityIndex; i++) {
            GameEntity entity = entities[i];
            if (entity != null) {
                g.setColor(entity.getColor());
                g.fillRect((int) entity.getX(), (int) entity.getY(), entity.getWidth(), entity.getHeight());
            }
        }
    }

}
