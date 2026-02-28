package org.example.xiangqi.core;

import java.util.List;

public class Game<B extends Board, CL extends CapturedLine> {
    public static final int ROWS = 10;
    public static final int COLUMNS = 9;


    protected final B board;

    protected final CL capturedRedPieceLine;
    protected final CL capturedBlackPieceLine;


    public Game(B board, CL capturedRedPieceLine, CL capturedBlackPieceLine) {
        this.board = board;
        this.capturedRedPieceLine = capturedRedPieceLine;
        this.capturedBlackPieceLine = capturedBlackPieceLine;
    }

    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (Board.isInvalidPosition(fromRow, fromCol) || Board.isInvalidPosition(toRow, toCol)) {
            return false;
        }

        if (board.isEmptyAt(fromRow, fromCol)) {
            System.out.println("No piece at the source position (" + fromRow + ", " + fromCol + ")");
            return false;
        }

        if (board.isEmptyAt(toRow, toCol)) {
            return board.attemptMove(fromRow, fromCol, toRow, toCol);
        }

        Piece capturedPiece = board.attemptCapture(fromRow, fromCol, toRow, toCol);
        if (capturedPiece != null) {
            if (capturedPiece.getColor() == PieceColor.RED) {
                capturedRedPieceLine.addPiece(capturedPiece);
            } else {
                capturedBlackPieceLine.addPiece(capturedPiece);
            }

            return true;
        }
        return false;
    }

    public void initialize() {
        board.addPiece(new Chariot(PieceType.RED_CHARIOT), 0, 0);
        board.addPiece(new Horse(PieceType.RED_HORSE), 0, 1);
        board.addPiece(new Elephant(PieceType.RED_ELEPHANT), 0, 2);
        board.addPiece(new Advisor(PieceType.RED_ADVISOR), 0, 3);
        board.addPiece(new General(PieceType.RED_GENERAL), 0, 4);
        board.addPiece(new Advisor(PieceType.RED_ADVISOR), 0, 5);
        board.addPiece(new Elephant(PieceType.RED_ELEPHANT), 0, 6);
        board.addPiece(new Horse(PieceType.RED_HORSE), 0, 7);
        board.addPiece(new Chariot(PieceType.RED_CHARIOT), 0, 8);
        board.addPiece(new Cannon(PieceType.RED_CANNON), 2, 1);
        board.addPiece(new Cannon(PieceType.RED_CANNON), 2, 7);
        for (int col = 0; col < COLUMNS; col += 2) {
            board.addPiece(new Soldier(PieceType.RED_SOLDIER), 3, col);
        }

        board.addPiece(new Chariot(PieceType.BLACK_CHARIOT), 9, 0);
        board.addPiece(new Horse(PieceType.BLACK_HORSE), 9, 1);
        board.addPiece(new Elephant(PieceType.BLACK_ELEPHANT), 9, 2);
        board.addPiece(new Advisor(PieceType.BLACK_ADVISOR), 9, 3);
        board.addPiece(new General(PieceType.BLACK_GENERAL), 9, 4);
        board.addPiece(new Advisor(PieceType.BLACK_ADVISOR), 9, 5);
        board.addPiece(new Elephant(PieceType.BLACK_ELEPHANT), 9, 6);
        board.addPiece(new Horse(PieceType.BLACK_HORSE), 9, 7);
        board.addPiece(new Chariot(PieceType.BLACK_CHARIOT), 9, 8);
        board.addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 1);
        board.addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 7);
        for (int col = 0; col < COLUMNS; col += 2) {
            board.addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, col);
        }
    }

    public List<int[]> getPossibleMoves(int row, int col) {
        return board.getPossibleMoves(row, col);
    }
}
