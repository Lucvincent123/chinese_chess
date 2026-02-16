package org.example.xiangqi.core;

import java.util.Objects;

public class Chariot extends Piece{
    public Chariot(TypesOfPiece type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) {
            return false; // Must move in a straight line
        }
        if (fromRow == toRow) { // Moving horizontally
            int step = (toCol - fromCol) > 0 ? 1 : -1;
            for (int col = fromCol + step; col != toCol; col += step) {
                if (chessBoard[fromRow][col] != null) {
                    return false; // Path is blocked
                }
            }
        } else { // Moving vertically
            int step = (toRow - fromRow) > 0 ? 1 : -1;
            for (int row = fromRow + step; row != toRow; row += step) {
                if (chessBoard[row][fromCol] != null) {
                    return false; // Path is blocked
                }
            }
        }
        return true;
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol) && !Objects.equals(chessBoard[toRow][toCol].getType().getColor(), this.type.getColor());
    }
}
