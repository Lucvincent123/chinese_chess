package org.example.xiangqi.core;

public class ChessBoard {
    public static final int ROWS = 10;
    public static final int COLUMNS = 9;

    // Position constants
    private static final int RIVER_START = 4;
    private static final int RIVER_END = 5;
    private static final int RED_BACK_ROW = 0;
    private static final int BLACK_BACK_ROW = 9;

    private final Piece[][] board = new Piece[ROWS][COLUMNS];
    private final Piece[][] temporaryBoard = new Piece[ROWS][COLUMNS];
    private final CapturedPieceLine capturedRedPieceLines = new CapturedPieceLine(true);
    private final CapturedPieceLine capturedBlackPieceLines = new CapturedPieceLine(false);
    private final int[] redGeneral = {-1, -1};
    private final int[] blackGeneral = {-1, -1};

    public ChessBoard() {
        // Default constructor
    }

    public void addPiece(Piece piece, int row, int column) {
        if (isInvalidPosition(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }

        updateGeneralPosition(piece, row, column);
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

    public void initializePieces() {
        initializeRedPieces();
        initializeBlackPieces();
    }

    private void initializeRedPieces() {
        addPiece(new Chariot(PieceType.RED_CHARIOT), RED_BACK_ROW, 0);
        addPiece(new Horse(PieceType.RED_HORSE), RED_BACK_ROW, 1);
        addPiece(new Elephant(PieceType.RED_ELEPHANT), RED_BACK_ROW, 2);
        addPiece(new Advisor(PieceType.RED_ADVISOR), RED_BACK_ROW, 3);
        addPiece(new General(PieceType.RED_GENERAL), RED_BACK_ROW, 4);
        addPiece(new Advisor(PieceType.RED_ADVISOR), RED_BACK_ROW, 5);
        addPiece(new Elephant(PieceType.RED_ELEPHANT), RED_BACK_ROW, 6);
        addPiece(new Horse(PieceType.RED_HORSE), RED_BACK_ROW, 7);
        addPiece(new Chariot(PieceType.RED_CHARIOT), RED_BACK_ROW, 8);
        addPiece(new Cannon(PieceType.RED_CANNON), 2, 1);
        addPiece(new Cannon(PieceType.RED_CANNON), 2, 7);
        addSoldiers(PieceType.RED_SOLDIER, 3);
    }

    private void initializeBlackPieces() {
        addPiece(new Chariot(PieceType.BLACK_CHARIOT), BLACK_BACK_ROW, 0);
        addPiece(new Horse(PieceType.BLACK_HORSE), BLACK_BACK_ROW, 1);
        addPiece(new Elephant(PieceType.BLACK_ELEPHANT), BLACK_BACK_ROW, 2);
        addPiece(new Advisor(PieceType.BLACK_ADVISOR), BLACK_BACK_ROW, 3);
        addPiece(new General(PieceType.BLACK_GENERAL), BLACK_BACK_ROW, 4);
        addPiece(new Advisor(PieceType.BLACK_ADVISOR), BLACK_BACK_ROW, 5);
        addPiece(new Elephant(PieceType.BLACK_ELEPHANT), BLACK_BACK_ROW, 6);
        addPiece(new Horse(PieceType.BLACK_HORSE), BLACK_BACK_ROW, 7);
        addPiece(new Chariot(PieceType.BLACK_CHARIOT), BLACK_BACK_ROW, 8);
        addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 1);
        addPiece(new Cannon(PieceType.BLACK_CANNON), 7, 7);
        addSoldiers(PieceType.BLACK_SOLDIER, 6);
    }

    private void addSoldiers(PieceType soldierType, int row) {
        for (int col : new int[]{0, 2, 4, 6, 8}) {
            addPiece(new Soldier(soldierType), row, col);
        }
    }

    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (isInvalidPosition(fromRow, fromCol) || isInvalidPosition(toRow, toCol)) {
            return false;
        }

        Piece piece = board[fromRow][fromCol];
        if (piece == null) {
            logMessage("No piece at the source position (" + fromRow + ", " + fromCol + ")");
            return false;
        }

        Piece targetPiece = board[toRow][toCol];
        if (targetPiece == null) {
            return attemptMove(piece, fromRow, fromCol, toRow, toCol);
        } else if (isEnemyPiece(piece, targetPiece)) {
            return attemptCapture(piece, targetPiece, fromRow, fromCol, toRow, toCol);
        }
        return false;
    }

    private boolean attemptMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        if (!piece.canMove(board, fromRow, fromCol, toRow, toCol)) {
            return false;
        }

        if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
            logMessage("Move would put own general in check. Move is not allowed.");
            return false;
        }

        logMessage("Attempting to move " + piece.getType() + " from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
        return moveAllowed(fromRow, fromCol, toRow, toCol, piece);
    }

    private boolean attemptCapture(Piece piece, Piece targetPiece, int fromRow, int fromCol, int toRow, int toCol) {
        if (!piece.canCapture(board, fromRow, fromCol, toRow, toCol)) {
            return false;
        }

        if (canThreatenGeneral(piece, fromRow, fromCol, toRow, toCol)) {
            logMessage("Move would put own general in check. Move is not allowed.");
            return false;
        }

        logMessage("Capturing " + targetPiece.getType() + " at (" + toRow + ", " + toCol + ")");
        addCapturedPiece(targetPiece);
        return moveAllowed(fromRow, fromCol, toRow, toCol, piece);
    }

    private boolean isEnemyPiece(Piece piece, Piece targetPiece) {
        return !targetPiece.getType().getColor().equals(piece.getType().getColor());
    }

    private void addCapturedPiece(Piece piece) {
        if (piece.getType().getColor() == PieceColor.RED) {
            capturedRedPieceLines.addPiece(piece);
        } else {
            capturedBlackPieceLines.addPiece(piece);
        }
    }

    private void logMessage(String message) {
        System.out.println(message);
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

    private void updateGeneralPositionAfterMove(Piece piece, int toRow, int toCol) {
        if (piece.getType() == PieceType.RED_GENERAL) {
            redGeneral[0] = toRow;
            redGeneral[1] = toCol;
        } else if (piece.getType() == PieceType.BLACK_GENERAL) {
            blackGeneral[0] = toRow;
            blackGeneral[1] = toCol;
        }
    }

    private boolean isInvalidPosition(int row, int col) {
        return 0 > row || row >= ROWS || 0 > col || col >= COLUMNS;
    }

    private boolean canThreatenGeneral(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
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

    @Override
    public String toString() {
        return capturedRedPieceLines + "\n" +
                buildColumnHeader() +
                buildBorder() +
                buildBoard() +
                buildBorder() +
                capturedBlackPieceLines;
    }

    private String buildColumnHeader() {
        StringBuilder header = new StringBuilder("  ");
        for (int j = 0; j < COLUMNS; j++) {
            header.append(j).append("    ");
        }
        return header.append("\n").toString();
    }

    private String buildBorder() {
        return " " + "#".repeat(COLUMNS * 5 - 1) + "\n";
    }

    private String buildBoard() {
        StringBuilder boardStr = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            boardStr.append(i).append("#");
            for (int j = 0; j < board[i].length; j++) {
                if (j > 0) {
                    boardStr.append("   ");
                }
                boardStr.append(getPieceSymbol(i, j));
            }
            boardStr.append("#\n");
        }
        return boardStr.toString();
    }

    private String getPieceSymbol(int row, int col) {
        Piece piece = board[row][col];
        if (piece != null) {
            return String.valueOf(piece.getType().getSymbol());
        }
        return isRiverRow(row) ? "--" : "++";
    }

    private boolean isRiverRow(int row) {
        return row == RIVER_START || row == RIVER_END;
    }


}
