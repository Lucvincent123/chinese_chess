package org.example.xiangqi.core;


public class ChessBoard {
    public static final int ROWS = 10;
    public static final int COLUMNS = 9;
    private final Piece[][] board = new Piece[ROWS][COLUMNS];
    private final CapturedPieceLines capturedRedPieceLines = new CapturedPieceLines(true);
    private final CapturedPieceLines capturedBlackPieceLines = new CapturedPieceLines(false);

    public ChessBoard() {

    }

    public void addPiece(Piece piece, int row, int column) {
        if (isValidPosition(row, column)) {
            board[row][column] = piece;
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid position: (" + row + ", " + column + ")");
        }
    }

    public void initializePieces() {
        // Initialize pieces in their starting positions
        // Red pieces
        addPiece(new Chariot(TypesOfPiece.RED_CHARIOT), 0, 0);
        addPiece(new Horse(TypesOfPiece.RED_HORSE), 0, 1);
        addPiece(new Elephant(TypesOfPiece.RED_ELEPHANT), 0, 2);
        addPiece(new Advisor(TypesOfPiece.RED_ADVISOR), 0, 3);
        addPiece(new General(TypesOfPiece.RED_GENERAL), 0, 4);
        addPiece(new Advisor(TypesOfPiece.RED_ADVISOR), 0, 5);
        addPiece(new Elephant(TypesOfPiece.RED_ELEPHANT), 0, 6);
        addPiece(new Horse(TypesOfPiece.RED_HORSE), 0, 7);
        addPiece(new Chariot(TypesOfPiece.RED_CHARIOT), 0, 8);
        addPiece(new Cannon(TypesOfPiece.RED_CANNON), 2, 1);
        addPiece(new Cannon(TypesOfPiece.RED_CANNON), 2, 7);
        addPiece(new Soldier(TypesOfPiece.RED_SOLDIER), 3, 0);
        addPiece(new Soldier(TypesOfPiece.RED_SOLDIER), 3, 2);
        addPiece(new Soldier(TypesOfPiece.RED_SOLDIER), 3, 4);
        addPiece(new Soldier(TypesOfPiece.RED_SOLDIER), 3, 6);
        addPiece(new Soldier(TypesOfPiece.RED_SOLDIER), 3, 8);

        // Black pieces
        addPiece(new Chariot(TypesOfPiece.BLACK_CHARIOT), 9, 0);
        addPiece(new Horse(TypesOfPiece.BLACK_HORSE), 9, 1);
        addPiece(new Elephant(TypesOfPiece.BLACK_ELEPHANT), 9, 2);
        addPiece(new Advisor(TypesOfPiece.BLACK_ADVISOR), 9, 3);
        addPiece(new General(TypesOfPiece.BLACK_GENERAL), 9, 4);
        addPiece(new Advisor(TypesOfPiece.BLACK_ADVISOR), 9, 5);
        addPiece(new Elephant(TypesOfPiece.BLACK_ELEPHANT), 9, 6);
        addPiece(new Horse(TypesOfPiece.BLACK_HORSE), 9, 7);
        addPiece(new Chariot(TypesOfPiece.BLACK_CHARIOT), 9, 8);
        addPiece(new Cannon(TypesOfPiece.BLACK_CANNON), 7, 1);
        addPiece(new Cannon(TypesOfPiece.BLACK_CANNON), 7, 7);
        addPiece(new Soldier(TypesOfPiece.BLACK_SOLDIER), 6, 0);
        addPiece(new Soldier(TypesOfPiece.BLACK_SOLDIER), 6, 2);
        addPiece(new Soldier(TypesOfPiece.BLACK_SOLDIER), 6, 4);
        addPiece(new Soldier(TypesOfPiece.BLACK_SOLDIER), 6, 6);
        addPiece(new Soldier(TypesOfPiece.BLACK_SOLDIER), 6, 8);
    }

    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        if (isValidPosition(fromRow, fromCol) && isValidPosition(toRow, toCol)) {
            Piece piece = board[fromRow][fromCol];
            if (piece != null) {
                System.out.println("Attempting to move " + piece.getType() + " from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
                Piece targetPiece = board[toRow][toCol];
                if (targetPiece == null && piece.canMove(board, fromRow, fromCol, toRow, toCol)) {
                    board[toRow][toCol] = piece;
                    board[fromRow][fromCol] = null;
                    return true;
                }
                if (targetPiece != null && piece.canCapture(board, fromRow, fromCol, toRow, toCol)) {
                    System.out.println("Capturing " + targetPiece.getType() + " at (" + toRow + ", " + toCol + ")");
                    if (targetPiece.getType().getColor().equals("red")) {
                        capturedRedPieceLines.addCapturedPiece(targetPiece);
                    } else {
                        capturedBlackPieceLines.addCapturedPiece(targetPiece);
                    }
                    board[toRow][toCol] = piece;
                    board[fromRow][fromCol] = null;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPosition(int row, int col) {
        return 0 <= row && row < ROWS && 0 <= col && col < COLUMNS;
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
