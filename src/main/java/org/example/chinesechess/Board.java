package org.example.chinesechess;

import org.example.chinesechess.pieces.*;

public class Board {
    private static final int ROWS = 10;
    private static final int COLS = 9;

    private Piece[][] board = new Piece[ROWS][COLS];

    // Board position constants (from ChessBoard)
    private int boardX = 50;
    private int boardY = 50;
    private int padding = 20;
    private int borderThickness = 4;
    private int cellSize = 50;

    private Piece selectedPiece = null;



    public Board() {
        initializePieces();
    }

    /**
     * Initialize all pieces on the board in their starting positions
     */
    private void initializePieces() {
        // ===== RED PIECES (Top side) =====

        // Red Chariots (車)
        board[0][0] = new Chariot(0, 0, Piece.PieceColor.RED);
        board[0][8] = new Chariot(0, 8, Piece.PieceColor.RED);

        // Red Horses (馬)
        board[0][1] = new Horse(0, 1, Piece.PieceColor.RED);
        board[0][7] = new Horse(0, 7, Piece.PieceColor.RED);

        // Red Elephants (象)
        board[0][2] = new Elephant(0, 2, Piece.PieceColor.RED);
        board[0][6] = new Elephant(0, 6, Piece.PieceColor.RED);

        // Red Advisors (士)
        board[0][3] = new Advisor(0, 3, Piece.PieceColor.RED);
        board[0][5] = new Advisor(0, 5, Piece.PieceColor.RED);

        // Red King (將)
        board[0][4] = new King(0, 4, Piece.PieceColor.RED);

        // Red Cannons (砲)
        board[2][1] = new Cannon(2, 1, Piece.PieceColor.RED);
        board[2][7] = new Cannon(2, 7, Piece.PieceColor.RED);

        // Red Soldiers (兵)
        board[3][0] = new Soldier(3, 0, Piece.PieceColor.RED);
        board[3][2] = new Soldier(3, 2, Piece.PieceColor.RED);
        board[3][4] = new Soldier(3, 4, Piece.PieceColor.RED);
        board[3][6] = new Soldier(3, 6, Piece.PieceColor.RED);
        board[3][8] = new Soldier(3, 8, Piece.PieceColor.RED);

        // ===== BLACK PIECES (Bottom side) =====

        // Black Chariots (車)
        board[9][0] = new Chariot(9, 0, Piece.PieceColor.BLACK);
        board[9][8] = new Chariot(9, 8, Piece.PieceColor.BLACK);

        // Black Horses (馬)
        board[9][1] = new Horse(9, 1, Piece.PieceColor.BLACK);
        board[9][7] = new Horse(9, 7, Piece.PieceColor.BLACK);

        // Black Elephants (相)
        board[9][2] = new Elephant(9, 2, Piece.PieceColor.BLACK);
        board[9][6] = new Elephant(9, 6, Piece.PieceColor.BLACK);

        // Black Advisors (士)
        board[9][3] = new Advisor(9, 3, Piece.PieceColor.BLACK);
        board[9][5] = new Advisor(9, 5, Piece.PieceColor.BLACK);

        // Black King (帥)
        board[9][4] = new King(9, 4, Piece.PieceColor.BLACK);

        // Black Cannons (砲)
        board[7][1] = new Cannon(7, 1, Piece.PieceColor.BLACK);
        board[7][7] = new Cannon(7, 7, Piece.PieceColor.BLACK);

        // Black Soldiers (兵)
        board[6][0] = new Soldier(6, 0, Piece.PieceColor.BLACK);
        board[6][2] = new Soldier(6, 2, Piece.PieceColor.BLACK);
        board[6][4] = new Soldier(6, 4, Piece.PieceColor.BLACK);
        board[6][6] = new Soldier(6, 6, Piece.PieceColor.BLACK);
        board[6][8] = new Soldier(6, 8, Piece.PieceColor.BLACK);
    }

    /**
     * Get piece at a specific position
     */
    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return null;
        }
        return board[row][col];
    }

    /**
     * Get all pieces on the board
     */
    public Piece[] getAllPieces() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != null) {
                    count++;
                }
            }
        }

        Piece[] pieces = new Piece[count];
        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != null) {
                    pieces[index++] = board[i][j];
                }
            }
        }
        return pieces;
    }

    /**
     * Convert board position (row, col) to pixel position
     */
    public int[] getPixelPosition(int row, int col) {
        int pixelX = boardX + borderThickness / 2 + padding + col * cellSize;
        int pixelY = boardY + borderThickness / 2 + padding + row * cellSize;
        return new int[]{pixelX, pixelY};
    }

    public int[] screenToBoard(int pixelX, int pixelY) {
        int col = (pixelX - boardX - borderThickness / 2 - padding) / cellSize;
        int row = (pixelY - boardY - borderThickness / 2 - padding) / cellSize;
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return null; // Out of bounds
        }
        return new int[]{row, col};
    }

    public void clickOnBoard() {
        if (selectedPiece != null) {
            // Handle move logic here (validate move, update board state, etc.)
            selectedPiece.setSelected(false);
            selectedPiece = null;
        }
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece piece) {
        if (selectedPiece != null) {
            selectedPiece.setSelected(false);
        }
        selectedPiece = piece;
        if (selectedPiece != null) {
            selectedPiece.setSelected(true);
        }
    }

    public boolean isValidMove(Piece piece, int targetRow, int targetCol) {
        // Implement move validation logic based on piece type and game rules
        // This is a placeholder and should be expanded with actual rules for each piece
        return true;
    }

    public void movePiece(Piece piece, int targetRow, int targetCol) {
        if (isValidMove(piece, targetRow, targetCol)) {
            // Move the piece on the board
            board[piece.getRow()][piece.getCol()] = null; // Remove from old position
            piece.setPosition(targetRow, targetCol); // Update piece position
            Piece capturedPiece = board[targetRow][targetCol]; // Check if there's a piece to capture
            if (capturedPiece != null) {
                // Handle captured piece (e.g., remove from game, update score, etc.)
                capturedPiece.setCaptured(true);
            }
            board[targetRow][targetCol] = piece; // Place in new position
        }
    }
}

