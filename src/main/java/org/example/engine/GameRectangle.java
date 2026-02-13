package org.example.engine;

import java.awt.Graphics;
import java.awt.Color;

public class GameRectangle implements DrawableEntity {
    private int x, y, width, height;
    private Color color;

    public GameRectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

}
