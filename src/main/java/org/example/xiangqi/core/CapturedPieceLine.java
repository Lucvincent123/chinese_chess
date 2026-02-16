package org.example.xiangqi.core;

public class CapturedPieceLine extends PieceLine{

    public CapturedPieceLine(boolean isRed) {
        super(isRed);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("#".repeat(MAX_PIECES * 3 / 2 + 2)).append("\n");
        String title = " Captured pieces: " + (isRed ? PieceColor.RED : PieceColor.BLACK);
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