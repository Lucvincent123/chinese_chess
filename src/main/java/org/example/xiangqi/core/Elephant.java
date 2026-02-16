package org.example.xiangqi.core;

public class Elephant extends Piece {
    public Elephant(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (type == PieceType.RED_ELEPHANT && toRow > 4) {
            return false; // Red Elephant cannot cross the river
        }
        if (type == PieceType.BLACK_ELEPHANT && toRow < 5) {
            return false; // Black Elephant cannot cross the river
        }
        if (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;
            return chessBoard[midRow][midCol] == null; // Check if the middle point is empty
        }
        return false;
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }
}
