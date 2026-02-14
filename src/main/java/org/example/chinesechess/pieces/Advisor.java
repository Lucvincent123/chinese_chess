package org.example.chinesechess.pieces;

import java.awt.*;

public class Advisor extends Piece {
    public Advisor(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "仕" : "士";  // Sĩ
    }

    @Override
    public String getType() {
        return "Advisor";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {

            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }

    }
}

