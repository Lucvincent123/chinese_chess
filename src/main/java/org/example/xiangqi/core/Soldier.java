package org.example.xiangqi.core;

public class Soldier extends Piece {
    public Soldier(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (type == PieceType.RED_SOLDIER) {
            if (toRow == fromRow + 1 && toCol == fromCol) {
                return true; // Move forward
            }
            return fromRow >= 5 && toRow == fromRow && Math.abs(toCol - fromCol) == 1; // Move sideways after crossing the river
        } else { // BLACK_SOLDIER
            if (toRow == fromRow - 1 && toCol == fromCol) {
                return true; // Move forward
            }
            return fromRow <= 4 && toRow == fromRow && Math.abs(toCol - fromCol) == 1; // Move sideways after crossing the river
        }
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }
}
