package org.example.xiangqi.core;

abstract public class Piece {
    protected final PieceType type;

    public Piece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    public PieceName getName() { return type.getName(); }

    public PieceColor getColor() { return type.getColor(); }

    public abstract boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol);

    public abstract boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol);

}
