package org.example.xiangqi.core;

public class CapturedPieceLines {
    public final static int MAX_PIECES = 16;
    private final Piece[] line = new Piece[MAX_PIECES];
    private int count = 0;
    private final boolean isRed;

    public CapturedPieceLines(boolean isRed) {
        this.isRed = isRed;
    }

    public void addCapturedPiece(Piece piece) {
        if (count < MAX_PIECES) {
            line[count] = piece;
            count++;
        } else {
            throw new IllegalStateException("Cannot capture more than " + MAX_PIECES + " pieces.");
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("#".repeat(MAX_PIECES * 3 / 2 + 2)).append("\n");
        String title = " Captured pieces: " + (isRed ? "Red" : "Black");
        string.append("#").append(title);
        string.append(" ".repeat(Math.max(0, MAX_PIECES * 3 / 2 - title.length()))).append("#\n");

        string.append("#").append("-".repeat(MAX_PIECES * 3 / 2)).append("#\n");
        string.append("#");
        for (int i = 0; i < MAX_PIECES / 2; i++) {
            if (line[i] != null) {
                string.append(line[i].getType().getSymbol()).append(" ");
            } else {
                string.append(" - ");
            }
        }
        string.append("#\n");
        string.append("#");
        for (int i = MAX_PIECES / 2; i < MAX_PIECES; i++) {
            if (line[i] != null) {
                string.append(line[i].getType().getSymbol()).append(" ");
            } else {
                string.append(" - ");
            }
        }
        string.append("#\n");
        string.append("#".repeat(MAX_PIECES * 3 / 2 + 2)).append("\n");
        return string.toString();
    }
}