package org.example.xiangqi.cli;

import org.example.xiangqi.core.CapturedLine;
import org.example.xiangqi.core.PieceColor;

public class CapturedLineCLI extends CapturedLine {
    public CapturedLineCLI(boolean isRed) {
        super(isRed);
    }

    @Override
    public String toString() {
        int borderWidth = MAX_PIECES * 3 / 2 + 2;

        return buildBorder(borderWidth) +
                buildHeader() +
                buildSeparator() +
                buildFirstRow() +
                buildSecondRow() +
                buildBorder(borderWidth);
    }

    private String buildBorder(int width) {
        return "#".repeat(width) + "\n";
    }

    private String buildHeader() {
        String title = " Captured pieces: " + (isRed ? PieceColor.RED : PieceColor.BLACK);
        return "#" + title +
                " ".repeat(Math.max(0, MAX_PIECES * 3 / 2 - title.length())) +
                "#\n";
    }

    private String buildSeparator() {
        return "#" + "-".repeat(MAX_PIECES * 3 / 2) + "#\n";
    }

    private String buildFirstRow() {
        return buildPieceRow(0, MAX_PIECES / 2);
    }

    private String buildSecondRow() {
        return buildPieceRow(MAX_PIECES / 2, MAX_PIECES);
    }

    private String buildPieceRow(int start, int end) {
        StringBuilder row = new StringBuilder("#");
        for (int i = start; i < end; i++) {
            if (line[i] != null) {
                row.append(line[i].getType().getSymbol()).append(" ");
            } else {
                row.append(" - ");
            }
        }
        row.append("#\n");
        return row.toString();
    }
}
