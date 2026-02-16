package org.example.xiangqi.core;


import java.util.Objects;

public class ChessBoard {
    public static final int ROWS = 10;
    public static final int COLUMNS = 9;
    private final Piece[][] board = new Piece[ROWS][COLUMNS];
    private final Piece[][] temporaryBoard = new Piece[ROWS][COLUMNS]; // For move validation and simulation
    private final CapturedPieceLine capturedRedPieceLines = new CapturedPieceLine(true);
    private final CapturedPieceLine capturedBlackPieceLines = new CapturedPieceLine(false);
    private int[] redGeneral = {-1, -1};// Initialize to invalid position
    private int[] blackGeneral = {-1, -1};// Initialize to invalid position


    public ChessBoard() {

    }

    public void addPiece(Piece piece, int row, int column) {
        if (isValidPosition(row, column)) {
            if (piece.getType() == PieceType.RED_GENERAL) {
                if (redGeneral[0] != -1 && redGeneral[1] != -1) {
                    throw new IllegalStateException("Red General already exists on the board.");
                }
                redGeneral[0] = row;
                redGeneral[1] = column;
            } else if (piece.getType() == PieceType.BLACK_GENERAL) {
                if (blackGeneral[0] != -1 && blackGeneral[1] != -1) {
                    throw new IllegalStateException("Black General already exists on the board.");
                }
                blackGeneral[0] = row;
                blackGeneral[1] = column;
            }
            board[row][column] = piece;
            temporaryBoard[row][column] = piece; // Keep temporary board in sync
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }
    }

    public void initializePieces() {
        // Initialize pieces in their starting positions
        // Red pieces
        addPiece(new Chariot(PieceType.RED_CHARIOT), 0, 0);
        addPiece(new Horse(PieceType.RED_HORSE), 0, 1);
        addPiece(new Elephant(PieceType.RED_ELEPHANT), 0, 2);
        addPiece(new Advisor(PieceType.RED_ADVISOR), 0, 3);
        addPiece(new General(PieceType.RED_GENERAL), 0, 4);
        addPiece(new Advisor(PieceType.RED_ADVISOR), 0, 5);
        addPiece(new Elephant(PieceType.RED_ELEPHANT), 0, 6);
        addPiece(new Horse(PieceType.RED_HORSE), 0, 7);
        addPiece(new Chariot(PieceType.RED_CHARIOT), 0, 8);
        addPiece(new Cannon(PieceType.RED_CANNON), 2, 1);
        addPiece(new Cannon(PieceType.RED_CANNON), 2, 7);
        addPiece(new Soldier(PieceType.RED_SOLDIER), 3, 0);
        addPiece(new Soldier(PieceType.RED_SOLDIER), 3, 2);
        addPiece(new Soldier(PieceType.RED_SOLDIER), 3, 4);
        addPiece(new Soldier(PieceType.RED_SOLDIER), 3, 6);
        addPiece(new Soldier(PieceType.RED_SOLDIER), 3, 8);

        // Black pieces
        addPiece(new Chariot(PieceType.BLACK_CHARIOT), 9, 0);
        addPiece(new Horse(PieceType.BLACK_HORSE), 9, 1);
        addPiece(new Elephant(PieceType.BLACK_ELEPHANT), 9, 2);
        addPiece(new Advisor(PieceType.BLACK_ADVISOR), 9, 3);
        addPiece(new General(PieceType.BLACK_GENERAL), 9, 4);
        addPiece(new Advisor(PieceType.BLACK_ADVISOR), 9, 5);
        addPiece(new Elephant(PieceType.BLACK_ELEPHANT), 9, 6);
        addPiece(new Horse(PieceType.BLACK_HORSE), 9, 7);
        addPiece(new Chariot(PieceType.BLACK_CHARIOT), 9, 8);
        addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 1);
        addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 7);
        addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, 0);
        addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, 2);
        addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, 4);
        addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, 6);
        addPiece(new Soldier(PieceType.BLACK_SOLDIER), 6, 8);
    }

    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (isValidPosition(fromRow, fromCol) && isValidPosition(toRow, toCol)) {
            Piece piece = board[fromRow][fromCol];
            if (piece != null) {
                System.out.println("Attempting to move " + piece.getType() + " from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
                Piece targetPiece = board[toRow][toCol];
                if (targetPiece == null && piece.canMove(board, fromRow, fromCol, toRow, toCol)) {
                    if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
                        System.out.println("Move would put own general in check. Move is not allowed.");
                        return false; // Move is not allowed as it would put own general in check
                    }
                    return moveAllowed(fromRow, fromCol, toRow, toCol, piece);
                }
                if (targetPiece != null && !Objects.equals(targetPiece.getType().getColor(), piece.getType().getColor()) && piece.canCapture(board, fromRow, fromCol, toRow, toCol)) {
                    System.out.println("Capturing " + targetPiece.getType() + " at (" + toRow + ", " + toCol + ")");
                    if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
                        System.out.println("Move would put own general in check. Move is not allowed.");
                        return false; // Move is not allowed as it would put own general in check
                    }

                    if (targetPiece.getType().getColor() == PieceColor.RED) {
                        capturedRedPieceLines.addPiece(targetPiece);
                    } else {
                        capturedBlackPieceLines.addPiece(targetPiece);
                    }
                    return moveAllowed(fromRow, fromCol, toRow, toCol, piece);
                }
            }
        }
        return false;
    }

    private boolean moveAllowed(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        if (piece.getType() == PieceType.RED_GENERAL) {
            redGeneral[0] = toRow;
            redGeneral[1] = toCol;
        } else if (piece.getType() == PieceType.BLACK_GENERAL) {
            blackGeneral[0] = toRow;
            blackGeneral[1] = toCol;
        }
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;
        return true;
    }

    private boolean isValidPosition(int row, int col) {
        return 0 <= row && row < ROWS && 0 <= col && col < COLUMNS;
    }

    private boolean canThreatenGeneral(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        Piece temporaryPiece = temporaryBoard[toRow][toCol];
        temporaryBoard[toRow][toCol] = piece; // Simulate the move
        temporaryBoard[fromRow][fromCol] = null;
        if (piece.getType() == PieceType.RED_GENERAL) {
            redGeneral[0] = toRow;
            redGeneral[1] = toCol;
        } else if (piece.getType() == PieceType.BLACK_GENERAL) {
            blackGeneral[0] = toRow;
            blackGeneral[1] = toCol;
        }
        PieceColor turn = piece.getType().getColor();
        int generalRow = (turn == PieceColor.RED) ? redGeneral[0] : blackGeneral[0];
        int generalCol = (turn == PieceColor.RED) ? redGeneral[1] : blackGeneral[1];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Piece opponentPiece = temporaryBoard[i][j];
                if (opponentPiece != null && opponentPiece.getType().getColor() != turn) {
                    if (opponentPiece.canCapture(temporaryBoard, i, j, generalRow, generalCol)) {
                        temporaryBoard[fromRow][fromCol] = piece; // Revert the move
                        temporaryBoard[toRow][toCol] = temporaryPiece; // Revert the move
                        return true; // Opponent can capture the general
                    }
                }
            }
        }
        return false; // Opponent cannot capture the general
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(capturedRedPieceLines).append("\n");
        string.append("  ");
        for (int j = 0; j < COLUMNS; j++) {
            string.append(j).append("    ");
        }
        string.append("\n");
        string.append(" ");
        string.append("#".repeat(COLUMNS * 5 - 1));
        string.append("\n");
        for (int i = 0; i < board.length; i++) {
            string.append(i).append("#");
            for (int j = 0; j < board[0].length; j++) {
                if (j > 0) {
                    string.append("   ");
                }
                Piece piece = board[i][j];
                if (piece != null) {
                    string.append(board[i][j].getType().getSymbol());
                } else {
                    if (i == 4 || i == 5) {
                        string.append("--");
                    } else {
                        string.append("++");
                    }
                }
            }
            string.append("#");
            string.append("\n");
        }
        string.append(" ");
        string.append("#".repeat(COLUMNS * 5 - 1));
        string.append("\n");
        string.append(capturedBlackPieceLines);
        return string.toString();
    }


}
