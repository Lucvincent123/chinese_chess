package org.example.xiangqi.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int ROWS = 10;
    public static final int COLUMNS = 9;

    // Position constants
    public static final int RIVER_START = 4;
    public static final int RIVER_END = 5;
    public static final int RED_BACK_ROW = 0;
    public static final int BLACK_BACK_ROW = 9;

    protected final Piece[][] board;
    private final Piece[][] temporaryBoard;

    private final int[] redGeneral = {-1, -1};
    private final int[] blackGeneral = {-1, -1};

    public Board() {
        board = new Piece[ROWS][COLUMNS];
        temporaryBoard = new Piece[ROWS][COLUMNS];
    }

    public boolean isEmptyAt(int row, int column) {
        if (isInvalidPosition(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }
        return board[row][column] == null;
    }

    public char getPieceSymbolAt(int row, int column) {
        if (isInvalidPosition(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }
        return board[row][column].getType().getSymbol();
    }

    static public boolean isInvalidPosition(int row, int col) {
        return 0 > row || row >= ROWS || 0 > col || col >= COLUMNS;
    }

    public void addPiece(Piece piece, int row, int column) {
        if (isInvalidPosition(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }

        if (piece.getName() == PieceName.GENERAL) {
            updateGeneralPosition(piece, row, column);
        }
        board[row][column] = piece;
        temporaryBoard[row][column] = piece;
    }

    private void updateGeneralPosition(Piece piece, int row, int column) {
        if (piece.getType() == PieceType.RED_GENERAL) {
            if (isGeneralAlreadyExists(redGeneral)) {
                throw new IllegalStateException("Red General already exists on the board.");
            }
            redGeneral[0] = row;
            redGeneral[1] = column;
        } else if (piece.getType() == PieceType.BLACK_GENERAL) {
            if (isGeneralAlreadyExists(blackGeneral)) {
                throw new IllegalStateException("Black General already exists on the board.");
            }
            blackGeneral[0] = row;
            blackGeneral[1] = column;
        }
    }

    private boolean isGeneralAlreadyExists(int[] generalPosition) {
        return generalPosition[0] != -1 && generalPosition[1] != -1;
    }

    public boolean attemptMove(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board[fromRow][fromCol];
        if (!piece.canMove(board, fromRow, fromCol, toRow, toCol)) {
            return false;
        }

        if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
            System.out.println("Move would put own general in check. Move is not allowed.");
            return false;
        }

        System.out.println("Attempting to move " + piece.getType() + " from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
        return moveAllowed(fromRow, fromCol, toRow, toCol, piece);
    }

    public Piece attemptCapture(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board[fromRow][fromCol];
        Piece targetPiece = board[toRow][toCol];
        if (!Board.isEnemyPiece(piece, targetPiece)) {
            System.out.println("Cannot capture own piece. Move is not allowed.");
            return null;
        }

        if (!piece.canCapture(board, fromRow, fromCol, toRow, toCol)) {
            return null;
        }

        if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
            System.out.println("Move would put own general in check. Move is not allowed.");
            return null;
        }

        System.out.println("Capturing " + targetPiece.getType() + " at (" + toRow + ", " + toCol + ")");
        return moveAllowed(fromRow, fromCol, toRow, toCol, piece) ? targetPiece : null;
    }

    public static boolean isEnemyPiece(Piece piece, Piece targetPiece) {
        return !targetPiece.getType().getColor().equals(piece.getType().getColor());
    }

    private boolean moveAllowed(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        updateGeneralPositionAfterMove(piece, toRow, toCol);
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;

        // Synchronize temporary board with actual board
        temporaryBoard[toRow][toCol] = piece;
        temporaryBoard[fromRow][fromCol] = null;

        return true;
    }

    protected boolean canThreatenGeneral(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        // Save original general position before simulation
        int[] originalGeneralPos = saveGeneralPosition(piece.getType().getColor());

        Piece capturedPiece = temporaryBoard[toRow][toCol]; // Save captured piece before simulation
        simulateMove(piece, fromRow, fromCol, toRow, toCol);

        PieceColor turn = piece.getType().getColor();
        int generalRow = (turn == PieceColor.RED) ? redGeneral[0] : blackGeneral[0];
        int generalCol = (turn == PieceColor.RED) ? redGeneral[1] : blackGeneral[1];

        boolean isUnderThreat = isGeneralUnderThreat(generalRow, generalCol, turn);

        revertMove(piece, fromRow, fromCol, toRow, toCol, capturedPiece);
        restoreGeneralPosition(piece.getType().getColor(), originalGeneralPos);

        return isUnderThreat;
    }

    private int[] saveGeneralPosition(PieceColor color) {
        if (color == PieceColor.RED) {
            return new int[]{redGeneral[0], redGeneral[1]};
        } else {
            return new int[]{blackGeneral[0], blackGeneral[1]};
        }
    }

    private void restoreGeneralPosition(PieceColor color, int[] originalPos) {
        if (color == PieceColor.RED) {
            redGeneral[0] = originalPos[0];
            redGeneral[1] = originalPos[1];
        } else {
            blackGeneral[0] = originalPos[0];
            blackGeneral[1] = originalPos[1];
        }
    }

    private void simulateMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        temporaryBoard[toRow][toCol] = piece;
        temporaryBoard[fromRow][fromCol] = null;
        updateGeneralPositionAfterMove(piece, toRow, toCol);
    }

    private void revertMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol, Piece capturedPiece) {
        temporaryBoard[fromRow][fromCol] = piece;
        temporaryBoard[toRow][toCol] = capturedPiece;
    }

    private void updateGeneralPositionAfterMove(Piece piece, int toRow, int toCol) {
        if (piece.getType() == PieceType.RED_GENERAL) {
            redGeneral[0] = toRow;
            redGeneral[1] = toCol;
        } else if (piece.getType() == PieceType.BLACK_GENERAL) {
            blackGeneral[0] = toRow;
            blackGeneral[1] = toCol;
        }
    }

    private boolean isGeneralUnderThreat(int generalRow, int generalCol, PieceColor turn) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Piece opponentPiece = temporaryBoard[i][j];
                if (opponentPiece != null && opponentPiece.getType().getColor() != turn) {
                    if (opponentPiece.canCapture(temporaryBoard, i, j, generalRow, generalCol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Piece getPieceAt(int row, int col) {
        if (isInvalidPosition(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + col + ")");
        }
        return board[row][col];
    }

    public List<int[]> getPossibleMoves(int row, int col) {
        List<int[]> possibleMoves = new ArrayList<>();
        Piece piece = getPieceAt(row, col);
        if (piece == null) {
            return possibleMoves; // No piece at the given position, return empty list
        }

        for (int toRow = 0; toRow < ROWS; toRow++) {
            for (int toCol = 0; toCol < COLUMNS; toCol++) {
                // Check if move is valid without modifying board
                try {
                    if (isValidMove(piece, row, col, toRow, toCol)) {
                        possibleMoves.add(new int[]{toRow, toCol});
                    }
                } catch (Exception e) {
                    System.out.println("Error checking move from (" + row + ", " + col + ") to (" + toRow + ", " + toCol + "): " + e.getMessage());
                    // Skip this move if there's an error
                }
            }
        }
        return possibleMoves;
    }

    private boolean isValidMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        // Can't move to same position
        if (fromRow == toRow && fromCol == toCol) {
            return false;
        }

        // Check if target is own piece
        Piece targetPiece = board[toRow][toCol];
        if (targetPiece != null && targetPiece.getType().getColor() == piece.getType().getColor()) {
            return false;
        }

        // Check if piece can move to that position
        boolean canMoveOrCapture = false;
        if (targetPiece == null) {
            // Empty cell - check if piece can move
            canMoveOrCapture = piece.canMove(board, fromRow, fromCol, toRow, toCol);
        } else {
            // Occupied cell - check if piece can capture
            canMoveOrCapture = piece.canCapture(board, fromRow, fromCol, toRow, toCol);
        }

        return canMoveOrCapture;
    }
}
