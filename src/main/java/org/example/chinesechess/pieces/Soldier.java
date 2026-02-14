package org.example.chinesechess.pieces;

import java.awt.*;

public class Soldier extends Piece {
    public Soldier(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "兵" : "卒";  // Tốt
    }

    @Override
    public String getType() {
        return "Soldier";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {
            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }
    }
}

