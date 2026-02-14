package org.example.chinesechess.pieces;

import java.awt.*;

public class Chariot extends Piece {
    public Chariot(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "俥" : "車";  // Xe
    }

    @Override
    public String getType() {
        return "Chariot";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {

            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }
    }
}

