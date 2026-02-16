package org.example.xiangqi.core;

public class Horse extends Piece {
    public Horse(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check for blocking piece
            if (rowDiff == 2) {
                return chessBoard[fromRow + (toRow - fromRow) / 2][fromCol] == null; // Check vertical block
            } else {
                return chessBoard[fromRow][fromCol + (toCol - fromCol) / 2] == null; // Check horizontal block
            }
        }
        return false;
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }
}
