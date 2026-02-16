package org.example.xiangqi.core;

public class Cannon extends Piece {
    public Cannon(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) {
            return false; // Must move in a straight line
        }
        return countPiecesInPath(chessBoard, fromRow, fromCol, toRow, toCol) == 0; // No pieces in the path
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow != toRow && fromCol != toCol) {
            return false; // Must move in a straight line
        }
        return countPiecesInPath(chessBoard, fromRow, fromCol, toRow, toCol) == 1; // Exactly one piece in the path
    }

    private int countPiecesInPath(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        int count = 0;
        if (fromRow == toRow) { // Moving horizontally
            for (int col = Math.min(fromCol, toCol) + 1; col < Math.max(fromCol, toCol); col++) {
                if (chessBoard[fromRow][col] != null) {
                    count++;
                }
            }
        } else { // Moving vertically
            for (int row = Math.min(fromRow, toRow) + 1; row < Math.max(fromRow, toRow); row++) {
                if (chessBoard[row][fromCol] != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
