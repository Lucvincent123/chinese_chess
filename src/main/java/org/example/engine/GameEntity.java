package org.example.engine;

import java.awt.*;
import java.awt.event.KeyListener;

public class GameEntity {
    private float x, y;
    private int width, height;
    private Color color = Color.RED;
    public GameEntity(float x, float y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void update() {
        // Update entity logic here
    }

    public void move(float dx, float dy) { this.x += dx; this.y += dy; }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
