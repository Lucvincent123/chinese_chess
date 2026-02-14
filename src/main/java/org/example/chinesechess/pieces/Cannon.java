package org.example.chinesechess.pieces;

import java.awt.*;

public class Cannon extends Piece {
    public Cannon(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "砲" : "炮";  // Pháo
    }

    @Override
    public String getType() {
        return "Cannon";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {

            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }
    }
}

