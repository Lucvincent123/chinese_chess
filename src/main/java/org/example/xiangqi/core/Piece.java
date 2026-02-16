package org.example.xiangqi.core;

abstract public class Piece {
    protected final PieceType type;

    public Piece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol);

    abstract boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol);

}
