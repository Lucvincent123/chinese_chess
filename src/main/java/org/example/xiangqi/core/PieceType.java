package org.example.xiangqi.core;

public enum PieceType {
    RED_CHARIOT(PieceColor.RED, PieceName.CHARIOT),
    RED_HORSE(PieceColor.RED, PieceName.HORSE),
    RED_CANNON(PieceColor.RED, PieceName.CANNON),
    RED_SOLDIER(PieceColor.RED, PieceName.SOLDIER),
    RED_ADVISOR(PieceColor.RED, PieceName.ADVISOR),
    RED_ELEPHANT(PieceColor.RED, PieceName.ELEPHANT),
    RED_GENERAL(PieceColor.RED, PieceName.GENERAL),
    BLACK_CHARIOT(PieceColor.BLACK, PieceName.CHARIOT),
    BLACK_HORSE(PieceColor.BLACK, PieceName.HORSE),
    BLACK_CANNON(PieceColor.BLACK, PieceName.CANNON),
    BLACK_SOLDIER(PieceColor.BLACK, PieceName.SOLDIER),
    BLACK_ADVISOR(PieceColor.BLACK, PieceName.ADVISOR),
    BLACK_ELEPHANT(PieceColor.BLACK, PieceName.ELEPHANT),
    BLACK_GENERAL(PieceColor.BLACK, PieceName.GENERAL);


    private final PieceColor color;
    private final PieceName name;
    private final char symbol;

    PieceType(PieceColor color, PieceName name) {
        this.name = name;
        this.color = color;
        this.symbol = getSymbolByNameAndColor(name, color);
    }

    public char getSymbol() {
        return symbol;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceName getName() {
        return name;
    }

    public static char getSymbolByNameAndColor(PieceName name, PieceColor color) {
        return switch (color + " " + name) {
            case "RED CHARIOT" -> '俥';
            case "RED HORSE" -> '傌';
            case "RED CANNON" -> '炮';
            case "RED SOLDIER" -> '兵';
            case "RED ADVISOR" -> '仕';
            case "RED ELEPHANT" -> '相';
            case "RED GENERAL" -> '帥';

            case "BLACK CHARIOT" -> '車';
            case "BLACK HORSE" -> '馬';
            case "BLACK CANNON" -> '砲';
            case "BLACK SOLDIER" -> '卒';
            case "BLACK ADVISOR" -> '士';
            case "BLACK ELEPHANT" -> '象';
            case "BLACK GENERAL" -> '將';

            default -> throw new IllegalArgumentException("Invalid color or name");
        };
    }
}
