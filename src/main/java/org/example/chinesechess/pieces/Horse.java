package org.example.chinesechess.pieces;

import java.awt.*;

public class Horse extends Piece {
    public Horse(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "傌" : "馬";  // Mã
    }

    @Override
    public String getType() {
        return "Horse";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {

            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }
    }
}

