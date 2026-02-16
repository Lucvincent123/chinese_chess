package org.example.xiangqi.core;

public class Cannon extends Piece {
    public Cannon(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return isStraightLine(fromRow, fromCol, toRow, toCol) &&
               countPiecesInPath(chessBoard, fromRow, fromCol, toRow, toCol) == 0;
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return isStraightLine(fromRow, fromCol, toRow, toCol) &&
               countPiecesInPath(chessBoard, fromRow, fromCol, toRow, toCol) == 1;
    }

    private boolean isStraightLine(int fromRow, int fromCol, int toRow, int toCol) {
        return fromRow == toRow || fromCol == toCol;
    }

    private int countPiecesInPath(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        int count;
        if (fromRow == toRow) {
            count = countHorizontalPath(chessBoard, fromRow, fromCol, toCol);
        } else {
            count = countVerticalPath(chessBoard, fromRow, toRow, fromCol);
        }
        return count;
    }

    private int countHorizontalPath(Piece[][] chessBoard, int row, int fromCol, int toCol) {
        int count = 0;
        for (int col = Math.min(fromCol, toCol) + 1; col < Math.max(fromCol, toCol); col++) {
            if (chessBoard[row][col] != null) {
                count++;
            }
        }
        return count;
    }

    private int countVerticalPath(Piece[][] chessBoard, int fromRow, int toRow, int col) {
        int count = 0;
        for (int row = Math.min(fromRow, toRow) + 1; row < Math.max(fromRow, toRow); row++) {
            if (chessBoard[row][col] != null) {
                count++;
            }
        }
        return count;
    }
}
